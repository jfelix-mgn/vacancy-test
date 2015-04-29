$(setupApp);

function setupApp() {

    var $uploadBtn = $('#upload-btn');
    var $uploadForm = $('#upload-form');
    var $uploadInput = $uploadForm.find('#upload-input');
    var $table = $('#document-list table');
    var $tbody = $table.find('tbody');

    $uploadBtn.click(function () {
        $uploadInput.click();
    });
    $uploadInput.change(function () {
        $uploadForm.submit();
    });
    $uploadForm.ajaxForm({
        cache: false,
        resetForm: true,
        beforeSubmit: function () {
            setProcess(true);
        },
        complete: function () {
            setProcess(false);
        },
        fail: function () {
            showError('Ошибка при загрузке документа')
        },
        success: onUpload
    });

    $tbody.find('tr').click(onRowClick);
}

function setProcess(process) {
    var $uploadBtn = $('#upload-btn');
    if (process)
        $uploadBtn.addClass('disabled');
    else
        $uploadBtn.removeClass('disabled');
}

function onUpload(resp) {
    if (resp.status === 'FAIL') {
        showError(resp.error);
    } else if (resp.status === 'OK') {
        appendToList(resp.document);
    } else {
        showError("Неизветный статус ответа");
    }
}

function showError(error) {
    var $alert = $('#upload-alert');
    $alert.html('<strong>Ошибка</strong> ' + error);
    $alert.fadeIn(200, function () {
        $alert.fadeOut(5000);
    });
}

function appendToList(doc) {
    var $docList = $('#document-list');
    $docList.css('display', 'inherit');
    var $tbody = $docList.find('table > tbody');
    var $row = $('<tr>' +
    '<td>' + doc.id + '</td>' +
    '<td><i class=" text-primary fa fa-download"></i></td> ' +
    '<td>' + doc.name + '</td>' +
    '<td>' + doc.time + '</td>' +
    '</tr>');
    $row.prependTo($tbody);
    $row.click(onRowClick);
}

function onRowClick() {
    var $row = $(this);
    var $table = $('#document-list table');
    var docId = $row.find('td:first-child').text();
    var downloadUrl = $table.attr('download-url') + '?' + $.param({documentId: docId});
    window.open(downloadUrl);
}