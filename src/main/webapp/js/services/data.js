categoriesApp.service("data", function () {
    var selectedList = {};
    return {
        getSelectedList: function () {
            return selectedList;
        },
        setSelectedList: function (list) {
            selectedList = list;
        }};
});
       