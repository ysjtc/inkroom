window.onload = function() {
    var modules = document.querySelectorAll(".section .section-title");
    for (let i = 0; i < modules.length; i++) {
        modules[i].onclick = function() {
            if (this.parentNode.classList.contains("active")) {
                $(this).next().slideUp();
                this.parentNode.classList.remove("active");
            } else {
                if (document.querySelector(".active")) {
                    $(".active ul").slideUp();
                    document.querySelector(".active").classList.remove("active");
                }
                this.parentNode.classList.add("active");
                $(this).next().slideDown();
            }

        }
    }

}