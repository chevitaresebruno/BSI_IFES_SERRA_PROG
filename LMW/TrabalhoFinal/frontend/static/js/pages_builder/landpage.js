import navbar_builder from "../components/navbar.js";
import { request_builder, build_by_request } from "../components/navbar.js";


function builder()
{
    navbar_builder("home");
    request_builder("home", "Home").then((output) => { build_by_request(output); });
}


document.addEventListener("DOMContentLoaded", builder);

