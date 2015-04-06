/**
 * 
 */

function loginCheck(){
	
	if(document.frm.userid.value.length ==0 ){
		alert("input the id!");
		frm.userid.focus();
		return false;		
	}
	
	if( document.frm.pwd.value == ""){
		alert("input the password!");
		frm.pwd.focus();
		return false;
	}
	return true;	
}