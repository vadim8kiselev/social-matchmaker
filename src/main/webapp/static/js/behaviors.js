function levelBehavior(levelDiv, data) {
    cross(false);
    enableLastLevel(false);

    if (data !== undefined) {
        let methods = data.methods;

        for (let iterate in methods) {
            if (methods.hasOwnProperty(iterate)) {
                let method = methods[iterate];
                levelDiv.appendChild(button(data.search, method.name, method.type, method.uri, method.parameter, method.color,
                    function (data) {
                        level(data)
                    }));
            }
        }
        $('.wrapper').append(levelDiv);
        cross(true);
    } else {
        levelDiv.appendChild(button(undefined, 'Search', 'GET', 'api/search', {}, '#f7f7f7',
            function (data) {
                level(data)
            }));
        $('.wrapper').append(levelDiv);
    }
}

function buttonBehavior(button, body, method, uri, parameter, isPerformButton, callback) {
    $(button).click(function (event) {
        event.preventDefault();

        let success = function (data) {
            loader(false);
            console.log(data);
            callback(data)
        };

        let error = function (error) {
            loader(false);
            console.log(error);
        };

        if (!button.parentElement.hasAttribute('disabled')) {
            if (parameter && typeof parameter === 'string') {
                let params = parameter.split(',');
                dialog(params,
                    function (parameters) {
                        if (isPerformButton) {
                            let type = parameters['type'] ? parameters['type'] : 'json';
                            let performSuccess = function (data) {
                                loader(false);
                                saveFile(data, type);
                                console.log(data);
                            };
                            defaultCall(method, composeURL(uri, parameters), body, type, performSuccess, error);
                        } else {
                            defaultCall(method, composeURL(uri, parameters), body, 'json', success, error);
                        }
                    });
            } else {
                defaultCall(method, composeURL(uri), body, 'json', success, error);
            }
        }
    });
}

function crossBehavior(crossImg) {
    $(crossImg).click(function () {
        $('.wrapper .block').last().remove();
        enableLastLevel(true);

        if ($('.wrapper .block').length <= 1) {
            cross(false);
        }
    });
}