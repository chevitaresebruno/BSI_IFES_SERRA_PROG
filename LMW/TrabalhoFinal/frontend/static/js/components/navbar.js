var pre_navbar_status = false;


export default function navbar_builder(pacote)
{
    const div = document.getElementById("navbar");
    const nav = document.createElement("nav");

    nav.className = "navbar";
    div.appendChild(nav);
    
    get_navbar_elements(pacote).then((resultado) => {
        resultado.forEach((item) => {
            menu_builder(pacote, item, nav);
        });
    })
}


async function get_navbar_elements(pacote)
{
    const post_info = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "pacote": pacote })
    };

    const response = (await fetch('/navbar_elements', post_info)).json();

    return response;
}


function menu_builder(pacote, nome, div)
{
    const a = document.createElement("a");
    a.textContent = nome;

    a.addEventListener("click", function() { request_builder(pacote, nome).then((output) => { build_by_request(output); }); });

    div.appendChild(a);
}


export async function request_builder(pacote, nome)
{
    const post_info = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "pacote": pacote, "file": nome })
    };  

    const response = (await fetch('/navbar_option', post_info)).json();

    return response;
}


export function build_by_request(value)
{
    const animation = document.getElementById("animation");
    const pre_navbar = document.getElementById("pre-navbar");
    const navbar = document.getElementById("navbar");    
    const pos_navbar = document.getElementById("pos-navbar");

    if(!pre_navbar_status)
    {
        pre_navbar.innerHTML = value.pre_navbar;
        animation.style.transition = 'transform 0.5s ease';
        animation.style.transform = `translateY(-${pre_navbar.offsetHeight}px)`;
        
        setTimeout(() => {
            animation.style.transform = `translateY(+${pre_navbar.offsetHeight}px)`;
        }, 500);
        pre_navbar_status = true;
    }
    else
    {
        pre_navbar.style.transition = 'transform 0.5s ease';
        pre_navbar.style.transform = 'translateY(0)';

        setTimeout(() => {
            pre_navbar.innerHTML = value.pre_navbar;
            pre_navbar.style.transform = 'translateY(-100%)';
    
        }, 500);

        pre_navbar_status = false;
    }
    


    pos_navbar.innerHTML = value.pos_navbar;
}

function mov_navbar()
{
    const pre_navbar = document.getElementById("pre-navbar");
    const navbar = document.getElementById("navbar");    
    const pos_navbar = document.getElementById("pos-navbar");
}