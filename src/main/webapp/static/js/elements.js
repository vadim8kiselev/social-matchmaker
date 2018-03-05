function notification(element, message, type) {
    element.notify(message, type);
}

function dialog(params, callback) {
    let parameters = {};
    for (let param in params) {
        if (params.hasOwnProperty(param)) {
            parameters[params[param]] = prompt("Please, fill value for parameter '" + params[param] + "'");
        }
    }
    callback(parameters);
}

function cross(enable) {
    if (enable) {
        let crossImg = document.createElement('img');
        crossImg.className += 'cross';
        crossImg.src += '/static/img/cross.png';

        crossBehavior(crossImg);

        $('.wrapper').append(crossImg);
    } else {
        $('.wrapper .cross').remove();
    }
}

function loader(enable) {
    if (enable) {
        let loader = document.createElement('div');
        loader.className += 'loader';
        $('body').append(loader);
    } else {
        $('.loader').remove();
    }
}

function level(data) {
    let levelDiv = document.createElement('div');
    levelDiv.className += 'block';

    levelBehavior(levelDiv, data);
}

function button(body, name, method, uri, parameter, color, callback) {
    let button = document.createElement('a');
    button.className += 'inner_block';
    button.innerText = name;
    button.style.backgroundColor = color;

    buttonBehavior(button, body, method, uri, parameter, name === 'Perform', callback);

    return button;
}