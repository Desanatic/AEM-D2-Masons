use(function () {
    var Constants = {
        DESCRIPTION_PROP: "jcr:description",
        DESCRIPTION_LENGTH: 50
    };

    var title = currentPage.getNavigationTitle() || currentPage.getTitle() || currentPage.getName();
    var description = properties.get(Constants.DESCRIPTION_PROP, "").substr(0, Constants.DESCRIPTION_LENGTH);
    var date = new Date();
    var stringDate = date.toString();


    return {
        title: title,
        description: description,
        textForExample: "This text got from example.js",
        currentDate: stringDate
    };
});