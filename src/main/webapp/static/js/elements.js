function notification(element, message, type) {
    element.notify(message, type);
}

function dialog(params, callback) {
    let dialog = document.createElement('div');
    dialog.className += 'dialog';

    let parameters = {};
    for (let param in params) {
        if (params.hasOwnProperty(param)) {

            let block = document.createElement('div');
            block.className += 'paramBlock';

            let label = document.createElement('span');
            label.className += 'label';
            label.innerText = params[param] + ': ';


            let input = document.createElement('input');
            input.className += 'inputField';

            $(input).change(function () {
                parameters[params[param]] = this.value;
            });

            block.appendChild(label);
            block.appendChild(input);
            dialog.appendChild(block);
        }
    }

    let ok = document.createElement('div');
    ok.className += 'ok';
    ok.innerText = 'Submit';

    dialog.appendChild(ok);

    $(ok).click(function () {
        $('.dialog').remove();
        callback(parameters);
    });

    $('body').append(dialog);

    setTimeout(function(){
        $('.dialog .inputField').first().focus();
    });
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
    button.className += 'button';
    button.innerText = name;
    button.style.backgroundColor = color;

    buttonBehavior(button, body, method, uri, parameter, name === 'Perform', callback);

    return button;
}