<html>
<head>
  <title>VENTAS MOVILES</title>
  <meta http-equiv="Expires" content="0">
  <script language="JavaScript">

var tree = new Array();

var doc;

var scrollHeight = 0;
var lastClicked  = 0;
var found        = false;
var imgpath      = "../img/";

var style = "BODY, DIV, TD, LAYER { font-size:10pt; font-family:verdana; font-weight: bold;}";
    style = style + "A:link, A:visited { color:#000080; text-decoration:none; }";
    style = style + "A:hover, A:active { color:#ff0000; text-decoration:none; }";
    style = style + "IMG { border-width: none; vertical-align: top; }";

var header = "<html>\r\n<head>\r\n<title>Tree menu page</title>\r\n<meta http-equiv='Expires' content='0'>\r\n<style>";
    header = header + style;
    header = header + "</style>\r\n</head>\r\n<body bgcolor='#ffffff'>\r\n<table border=0 cellpadding=1 cellspacing=1>";

var footer = "</table>\r\n</body>\r\n</html>";

var connectors = new Array();
connectors[0] = imgpath+"0.gif";
connectors[1] = imgpath+"L.gif";
connectors[2] = imgpath+"Lm.gif";
connectors[3] = imgpath+"Lp.gif";
connectors[4] = imgpath+"I.gif";
connectors[5] = imgpath+"T.gif";
connectors[6] = imgpath+"Tm.gif";
connectors[7] = imgpath+"Tp.gif";

var symbols = new Array();
symbols[0] = imgpath+"Page.gif";
symbols[1] = imgpath+"Open.gif";
symbols[2] = imgpath+"Closed.gif";
symbols[3] = imgpath+"Disk.gif";

function Node(id,name,url,target,icon) {
    this.isContainer = isContainer;
    this.addBranch = addBranch;
    this.getBranch = getBranch;
    this.getCode = getCode;
    this.index = tree.length;
    if (id != "") this.id = id;
    else this.id = this.index;
    this.name = name;
    this.expanded = false;
    if (url) this.url = url;
    // else this.url = "";
    if (target) this.target = target;
    // else this.target = "";
    if (icon) this.icon = icon;
    else this.icon = "";
    tree[this.index] = this;
}

function isContainer() {
    return (this.branches && (this.branches.length > 0));
}

function addBranch(node) {
    if (!this.branches) this.branches = new Array();
    if (isNaN(node)) this.branches[this.branches.length] = node.index;
    else this.branches[this.branches.length] = node;
}

function getBranch(index) {
    return tree[this.branches[index]];
}

function getFromRoot() {
    var subnode = tree[0].getBranch(arguments[0]);
    var i=1;
    while (i<arguments.length) {
        subnode = subnode.getBranch(arguments[i]);
        i++;
    }
    return subnode;
}

function getCode(level,legacies,last) {
    if (this.index == lastClicked) found = true;
    if (!found) scrollHeight += 18;
    var code = "<tr><td nowrap>";
    for (var i=1; (i<level) && (i<legacies.length); i++) {
        code = code + "<img src='"+legacies[i]+"'>";
    }
    code = code + "<a href='JavaScript:parent.click("+this.index+")'";
    code = code + ">";
    symbol = 0;
    if (this.branches) {
        if (this.expanded) symbol = 1;
        else symbol = 2;
    }
    if (last) connector = 1
    else connector = 5;
    if (level == 0) code = code + "<img src='"+symbols[3]+"' border='0'>";
    else {
        code = code + "<img src='"+connectors[connector+symbol]+"' border='0'>";
        if (this.icon != "") code = code + "<img src='"+this.icon+"' border='0'>";
        else code = code + "<img src='"+symbols[symbol]+"' border='0'>";
    }
    code = code + this.name; + "</a></td></tr>\r\n";
    if (this.expanded && this.branches) {
        if (connector == 1) legacies[level] = connectors[0];
        else if (connector == 5) legacies[level] = connectors[4];
        for (var i=0; i<(this.branches.length-1); i++) {
            code = code + tree[this.branches[i]].getCode(level+1,legacies,false);
        }
        code = code + tree[this.branches[i]].getCode(level+1,legacies,true);
    }
    return code;
}

function click(index) {
    found = false;
    scrollHeight = 0;
    lastClicked = index;
    tree[index].expanded = !tree[index].expanded;
    if (tree[index].isContainer()) paintMenu();
    if (tree[index].url) {
        if (tree[index].target) window.open(tree[index].url,tree[index].target)
        else frames["workarea"].location.replace(tree[index].url);
    }
    else {
        if (tree[index].isContainer()) defaultURL = defaultContainerURL;
        else defaultURL = defaultTerminalURL;
        defaultURL = defaultURL + "?id=" + tree[index].id;
        if (tree[index].target) window.open(defaultURL,tree[index].target)
        else frames["workarea"].location.replace(defaultURL);
    }
}

function paintMenu() {
    if (doc.images) doc.open("text/html", "replace")
    else doc.open("text/html");
    doc.writeln(header + tree[0].getCode(0,new Array(),true) + footer);
    doc.close();
    frames["treearea"].scroll(0,scrollHeight);
}

  </script>
  <script language="JavaScript" src="./js/contenidoVendedor.js"></script>

</head>
<frameset frameborder="no" cols="200,*">
  <frame name="treearea" src="./jsp/treemenu.htm" leftmargin="5" topmargin="5" marginheight="5" marginwidth="0"/>
  <frame name="workarea" src="/Commerce/jsp/empty.htm" marginheight="0" marginwidth="0"/>
</frameset>
<noframes>
  <body bgcolor="#FFFFFF">
    Su navegador no soporta frames, descargue de Internet una nueva versión
  </body>
</noframes>
</html>