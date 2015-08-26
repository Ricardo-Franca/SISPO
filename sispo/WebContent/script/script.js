
function validarQtdeCaracteres(valor,max)    
{    
       
   if(valor.length < max)
   {
	   return true;
   }else
	{         
    	alert('Insira no máximo ' + max + ' caracter(es).');  
   		return false;
	}    
}