function load(method, uri, data, parameters, callback) {
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
            console.log(data);
            callback(data)
        },
        error: function (error) {
            alert('An error has occurred: ' + error.responseCode)
        }
    });
}

function createButton(body, name, method, uri, parameters, color, callback) {
    let button = document.createElement('span');
    button.className += 'inner_block';
    button.innerText = name;
    button.style.backgroundColor = color;

    $(button).click(function () {
        if (!button.parentElement.hasAttribute('disabled')) {

            for (let parameter in parameters) {
                if (parameters.hasOwnProperty(parameter)) {
                    parameters[parameter] = prompt("Please, fill value for parameter '" + parameter + "'"); // TODO:
                }
            }

            load(method, uri, body, parameters, callback);
        }
    });

    return button
}

function createLevel(data) {
    disableLastLevel();

    let level = document.createElement('div');
    level.className += 'block';

    if (data !== undefined) {
        let methods = data.methods;

        for (let iterate in methods) {
            if (methods.hasOwnProperty(iterate)) {
                let method = methods[iterate];

                let parameters = {};
                if (method.parameter) {
                    parameters[method.parameter] = '';
                }

                let button = createButton(
                    data.search,
                    method.name,
                    method.type,
                    method.uri,
                    parameters,
                    method.color,
                    function (data) {
                        createLevel(data)
                    });
                level.appendChild(button);
            }
        }

    } else {
        let button = createButton(
            undefined,
            'Search',
            'GET',
            'api/start',
            {},
            '#eaeaea',
            function (data) {
                createLevel(data)
            });
        level.appendChild(button);
    }

    $('.wrapper').append(level);
}

function disableLastLevel() {
    $('.wrapper .block').last().attr("disabled", 'true');
}

function removeLastLevel() {
    $('.wrapper .block').last().remove()
}

$(document).ready(function () {
    createLevel(undefined);
});
