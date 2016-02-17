
var css = "body { -webkit-filter: none; }";

var head = document.getElementsByTagName("head")[0];

try {
    var oldStyles = document.getElementsByTagName("style");
    var oldStyle = oldStyles[oldStyles.length - 1];
    head.removeChild(oldStyle);
} catch(ex) { /* Do nothing */ }

var style = document.createElement("style");

style.type = "text/css";
if (style.styleSheet) {
    style.styleSheet.cssText = css;
} else {
    style.appendChild(document.createTextNode(css));
}

//injecting the css to the head
head.appendChild(style);