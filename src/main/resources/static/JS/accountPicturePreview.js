function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	var id='#imagePreview';
        	$(id).attr('src', e.target.result);
            
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$('input[type="file"]').change(function(){
    if(this.files[0].size > 5000000){
        alert("Merci de renseigner une image de moins de 5 Mo");
        this.value = "";
    }
    else{
    readURL(this);
    }
});

