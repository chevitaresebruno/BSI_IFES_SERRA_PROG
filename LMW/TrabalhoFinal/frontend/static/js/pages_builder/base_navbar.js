import navbar_builder from "../components/navbar.js";
import { request_builder, build_by_request } from "../components/navbar.js";


function builder()
{
    navbar_builder();
}


document.addEventListener("DOMContentLoaded", builder);

