function load(method, uri, data, parameters, callback) {
    toggleLoader(true);
    let urlParams = jQuery.param(parameters);
    $.ajax({
        type: method,
        url: 'http://localhost:8080/' + uri + (urlParams ? '?' + urlParams : ''),
        async: true,
        data: JSON.stringify(data),
        dataType: 'json',
        processData: false,
        contentType: 'application/json',
        success: function (data) {
            toggleLoader(false);
            callback(data)
        },
        error: function (error) {
            toggleLoader(false);
            alert('An error has occurred: ' + error.responseCode)
        }
    });
}

function createDialog(params, callback) {
    let parameters = {};
    for (let param in params) {
        if (params.hasOwnProperty(param)) {
            parameters[params[param]] = prompt("Please, fill value for parameter '" + params[param] + "'");
        }
    }
    callback(parameters);
}

function createButton(body, name, method, uri, parameter, color, callback) {
    let button = document.createElement('span');
    button.className += 'inner_block';
    button.innerText = name;
    button.style.backgroundColor = color;

    $(button).click(function () {
        if (!button.parentElement.hasAttribute('disabled')) {
            if (parameter && typeof parameter === 'string') {
                let params = parameter.split(',');
                createDialog(params,
                    function(parameters) {
                        load(method, uri, body, parameters, callback);
                    });
            } else {
                load(method, uri, body, {}, callback);
            }
        }
    });
    return button
}

function createLevel(data) {
    let wrapper = $('.wrapper');
    toggleCross(false);
    toggleLastLevel(false);

    let level = document.createElement('div');
    level.className += 'block';

    if (data !== undefined) {
        let methods = data.methods;

        for (let iterate in methods) {
            if (methods.hasOwnProperty(iterate)) {
                let method = methods[iterate];
                level.appendChild(createButton(data.search, method.name, method.type, method.uri, method.parameter, method.color,
                    function (data) {
                        createLevel(data)
                    }));
            }
        }
        let cross = toggleCross(true);
        wrapper.append(level);
        wrapper.append(cross);
    } else {
        level.appendChild(createButton(undefined, 'Search', 'GET', 'api/start', {}, '#f7f7f7',
            function (data) {
                createLevel(data)
            }));
        wrapper.append(level);
    }
}

function toggleCross(enable) {
    if (enable) {
        let cross = document.createElement('img');
        cross.className += 'cross';
        cross.src += '/static/img/cross.png';

        $(cross).click(function () {
            $('.wrapper .block').last().remove();
            toggleLastLevel(true);

            if($('.wrapper .block').length <= 1) {
                toggleCross(false);
            }
        });
        return cross;
    } else {
        $('.wrapper .cross').remove();
    }
}

function toggleLastLevel(enable) {
    if (enable) {
        $('.wrapper .block').last().removeAttr("disabled");
    } else {
        $('.wrapper .block').last().attr("disabled", 'true');
    }
}

function toggleLoader(enable) {
    if (enable) {
        let loader = document.createElement('div');
        loader.className += 'loader';
        $('body').append(loader);
    } else {
        $('.loader').remove();
    }
}

$(document).ready(function () {
    createLevel();
});
