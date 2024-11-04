import page_builder from "./builder.js";
import add_event_listners from "./roll.js"


function awake()
{
    page_builder();
    add_event_listners();
};


document.addEventListener('DOMContentLoaded', awake);

