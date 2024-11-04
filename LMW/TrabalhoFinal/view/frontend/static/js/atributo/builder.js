import { close_popup } from "./popup.js";


export default function page_builder()
{
    build_atributos();
    build_popup_close_buton();
}


function build_popup_close_buton()
{
    const buton = document.getElementById("close_popup");
    buton.onclick = close_popup;
}


function build_atributos()
{
    const div = document.getElementById("atributos");
    
    for (const key in jsonData)
    {
        const atributo = document.createElement("div");
        atributo.className = "atributo";
        div.appendChild(atributo);

        add_attribute(atributo, key);
        add_img(atributo, key);        
    }   
}

function add_attribute(div, key)
{
    const h2 = document.createElement("h2");
    h2.textContent = `${key}: ${jsonData[key]}`;

    div.appendChild(h2);
}

function add_img(div, key)
{
    const img = document.createElement("img", `src=; width='20px'; id='image-${key}'`);
    img.setAttribute("src", d20_img);
    img.setAttribute("width", "20px");
    img.setAttribute("id", `image-${key}`);

    div.appendChild(img);
}

