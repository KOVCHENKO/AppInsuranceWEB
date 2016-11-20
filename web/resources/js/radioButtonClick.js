function radiobuttonClick(thisObj, thisEvent) {
    var form = document.getElementById("view<%=renderResponse.getNamespace()%>:form1");
    var firstRadioButtonName = "view<%=renderResponse.getNamespace()%>:form1:table1:"+0+":radio1";
    var isInDatatable = false;
    var rowId = -1;
    for(i=0; i<form.elements.length; i++){
        if(form.elements[i].name == firstRadioButtonName){
            isInDatatable = true;
        }
        if(isInDatatable == true){
            rowId = rowId+1;
        }
        radioButtonName = "view<%=renderResponse.getNamespace()%>:form1:table1:"+rowId+":radio1";
        //Deselect all radio buttons first(if this element is a radio button inside your datatable)
        if(form.elements[i].name == radioButtonName){
            form.elements[i].checked = false;
            getRow(form.elements[i]).className = "";
        }
    }
    thisObj.checked = true;                       //set this radio button to selected state
    getRow(thisObj).className = "rowHighlighted"; //highlight the selected row
}