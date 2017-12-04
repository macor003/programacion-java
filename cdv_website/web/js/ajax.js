/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fsj_validaInput2( nombre, mensaje ){
    if ((eval("document.f_forma." + nombre + ".value == ''")) ) {           
        alert("Debe indicar " + mensaje );
        eval("document.f_forma." + nombre + ".focus()");
        return false;
    }
    return true;
}

function AgregarProductoXML(id_producto, valor){
    if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari

      xmlhttpagregarproducto=new XMLHttpRequest();
      xmlhttpagregarproducto.onreadystatechange = processReqChangeAgregarProducto;
      xmlhttpagregarproducto.open("GET","cdv_AjaxAgregarProducto.jsp?id_producto="+id_producto+"&valor="+valor,true);
      xmlhttpagregarproducto.send(null);
    }

    else if (window.ActiveXObject){// code for IE6, IE5
//	
      xmlhttpagregarproducto=new ActiveXObject("Microsoft.XMLHTTP");
      xmlhttpagregarproducto.onreadystatechange = processReqChangeAgregarProducto;
      xmlhttpagregarproducto.open("GET","cdv_AjaxAgregarProducto.jsp?id_producto="+id_producto+"&valor="+valor,true);
      xmlhttpagregarproducto.send();

    }
}

function processReqChangeAgregarProducto(){
    
    if(xmlhttpagregarproducto.readyState == 4){
        var carrito = document.getElementById("num_carrito");
        carrito.innerHTML = xmlhttpagregarproducto.responseText;
        //location.href="sigCoordinadorEliminado.jsp";
    }
}
