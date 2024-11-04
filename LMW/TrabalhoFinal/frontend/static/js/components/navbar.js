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
    const response = (await fetch(`/navbar_elements/${pacote}`)).json();
    return response;
}


function menu_builder(pacote, nome, div)
{
    console.log(pacote, nome);
    const a = document.createElement("a");
    a.textContent = nome;
    a.href = `/navbar_option/${pacote}/${nome}`

    div.appendChild(a);
}

