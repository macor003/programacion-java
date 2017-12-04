function fsj_validaSelect( nombre, mensaje ){
            if (eval("document.f_forma." + nombre + ".value === 'su'") ) {           
            alert("Debe seleccionar " + mensaje );
            eval("document.f_forma." + nombre + ".focus()");
            return false;
            }
        return true;
        }

function fsj_validaSelect2( nombre, mensaje ){
            if (eval("document.getElementById('" + nombre + "').value === 'su'") ) {           
            alert("Debe seleccionar " + mensaje );
            eval("document.getElementById('" + nombre + "').focus()");
            return false;
            }
        return true;
        }        
        
function fsj_validaInput( nombre, mensaje ){
            if (eval(("document.f_forma." + nombre + ".value === 'USUARIO DESCONOCIDO'"))  ) {           
            alert("Debe indicar " + mensaje );
            eval("document.f_forma." + nombre + ".focus()");
            return false;
            }
        return true;
        }
        
function fsj_validaInput2( nombre, mensaje ){            
            if ((eval("document.f_forma." + nombre + ".value === ''")) ) {           
            alert("Debe indicar " + mensaje );
            eval("document.f_forma." + nombre + ".focus()");
            return false;
            }
          return true;
        }
function fsj_validaTextarea (nombre, mensaje){
           if ((eval("document.f_forma." + nombre + ".value.length == 0")) ) {           
            alert("Debe ingresar una " + mensaje );
            eval("document.f_forma." + nombre + ".focus()");
            return false;
            } 
}