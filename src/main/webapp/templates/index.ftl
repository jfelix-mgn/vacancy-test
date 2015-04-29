<#-- @ftlvariable name="documents" type="java.util.List<vacancy.entity.Document>" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Vacancy Test</title>

    <link href="<@spring.url relativeUrl='/assets/plugins/normalize/normalize.css'/>" rel="stylesheet" type="text/css">
    <link href="<@spring.url relativeUrl='/assets/plugins/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"
          type="text/css">
    <link href="<@spring.url relativeUrl='/assets/plugins/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet"
          type="text/css">
    <link href="<@spring.url relativeUrl='/assets/css/app.css'/>" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <header>
        <div class="page-header">
            <h1>Тестовое задание <a href="<@spring.url relativeUrl='/reference'/>">
                <small>Документация API</small>
            </a></h1>

        </div>
    </header>
    <div class="row upload">
        <div class="col-md-offset-3 col-md-6 text-center">
            <h3>Загрузите XML документ для сохранения в БД</h3>
            <button type="button" id="upload-btn" class="btn btn-lg btn-primary"><i class="fa fa-upload"></i></button>
            <div style="display: none;">
                <form id="upload-form" role="form" action="<@spring.url relativeUrl='/api/upload'/>" method="POST"
                      enctype="multipart/form-data">
                    <input type="file" name="file" id="upload-input" accept="*.xml">
                </form>
            </div>
            <div id="upload-alert" class="alert alert-danger" role="alert" style="display: none"></div>
        </div>
    </div>
    <div class="row" id="document-list" <#if !documents?has_content>style="display: none"</#if>>
        <div class="col-md-offset-3 col-md-6 text-center">
            <h3>Загруженные документы</h3>
        </div>
        <div class="col-md-offset-3 col-md-6">
            <table class="table table-hover" download-url="<@spring.url relativeUrl='/api/download'/>">
                <thead>
                <tr>
                    <th></th>
                    <th width="30px"></th>
                    <th width="150px"></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list documents as doc>
                <tr>
                    <td>${doc.id?c}</td>
                    <td><i class="text-primary fa fa-download"></i></td>
                    <td>Документ-${doc.id?c}</td>
                    <td>${doc.formattedTime}</td>
                </tr>
                </a>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<@spring.url relativeUrl='/assets/plugins/jquery/jQuery-2.1.3.min.js'/>" type="text/javascript"></script>
<script src="<@spring.url relativeUrl='/assets/plugins/jquery.form/jquery.form.min.js'/>"
        type="text/javascript"></script>
<script src="<@spring.url relativeUrl='/assets/plugins/bootstrap/js/bootstrap.min.js'/>"
        type="text/javascript"></script>
<script src="<@spring.url relativeUrl='/assets/js/app.js'/>" type="text/javascript"></script>
</body>
</html>