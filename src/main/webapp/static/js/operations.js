function call(method, url, async, data, dataType, processData, contentType, success, error) {
    loader(true);
    console.log(url);
    $.ajax({
        type: method,
        url: url,
        async: async,
        data: data,
        dataType: dataType,
        processData: processData,
        contentType: contentType,
        success: function (body) {
            loader(false);
            notification($, 'Success', 'success');
            success(body);
        },
        error: function (exception) {
            loader(false);
            notification($, 'Fail', 'warn');
            error(exception);
        }
    });
}

function defaultCall(method, url, data, dataType, success, error) {
    call(method, url, true, JSON.stringify(data), dataType, false, 'application/json', success, error);
}

function composeURL(uri, parameters) {
    let url = 'http://localhost:8080/' + uri;
    if (parameters) {
        let urlParams = jQuery.param(parameters);
        url += (urlParams ? '?' + urlParams : '');
    }
    return url;
}

function saveFile(data, extension) {
    if (extension === 'xml') {
        let oSerializer = new XMLSerializer();
        data = oSerializer.serializeToString(data);
    } else if (extension === 'json') {
        data = JSON.stringify(data, null, 4);
    }

    let fileData = [data];
    let blob = new Blob(fileData);

    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        window.navigator.msSaveOrOpenBlob(blob, "download." + extension);
    } else {
        const data = window.URL.createObjectURL(blob);
        let link = document.createElement('a');
        link.href = data;
        link.download = "download." + extension;
        link.click();
        setTimeout(function () {
            window.URL.revokeObjectURL(data)
        }, 100);
    }
}

function enableLastLevel(enable) {
    if (enable) {
        $('.wrapper .block').last().removeAttr("disabled");
    } else {
        $('.wrapper .block').last().attr("disabled", 'true');
    }
}