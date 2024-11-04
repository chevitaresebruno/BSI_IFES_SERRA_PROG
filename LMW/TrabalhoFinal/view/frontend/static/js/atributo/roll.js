import { open_popup } from "./popup.js"


function roll_attribute(valor)
{
    open_popup();

    const post_info = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ numero: valor })
    };

    fetch('/roll_attribute', post_info)
    .then(response => response.json())
    .then(data => {
        const text = document.getElementById('resultDisplay');
        text.innerText = `${data.total} = ${data.rolagem}[d20] + ${data.buffs}`;
        critico(text, data.critico);
    });
};

function critico(text, option)
{
    if(option == 1)
        { text.style.color = 'green'; }
    else if(option == -1)
        { text.style.color = 'red'; }
    else 
        { text.style.color = 'black'; }
}


export default function add_event_listners()
{
    for(const key in jsonData)
    {
        const value = jsonData[key];
        document.getElementById(`image-${key}`).addEventListener('click', function(){roll_attribute(value)});
    }
};