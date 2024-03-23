/**
 * Console log message
 */
console.log("hi");

// SIDEBAR Toggle Function
const ToggleSidebar = () => {
    // If sidebar is visible, hide it
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display", "none");
        $(".main-content").css("margin-left", "0%");
    }
    // If sidebar is hidden, show it
    else{
        $(".sidebar").css("display", "block");
        $(".main-content").css("margin-left", "18%");
    }
};

// SEARCH FUNCTIONALITY
const search = () => {
    // Get the search query
    let query = $("#searchBox").val();

    // If search query is empty, hide search results
    if(query == ''){
        $(".search-result").hide();
    }
    // If search query is not empty, send request to server
    else{
        // Construct URL for search request
        let url = `http://localhost:8086/search/${query}`;

        // Fetch data from server
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                // Generate search result HTML
                let text = `<div class='list-group'>`;

                // Loop through each contact and create a link
                data.forEach((contact)=>{
                    text += `<a href='/user/${contact.cid}/contact' class='list-group-item list-group-item-action'> ${contact.name} </a>`;
                });

                text += `</div`;

                // Display search results
                $(".search-result").html(text);
                $(".search-result").show();
            });
    }
};


