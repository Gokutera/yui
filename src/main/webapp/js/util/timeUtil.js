function getMinFromNow(value) {
    var date = new Date();
    date.setMinutes(date.getMinutes() + value);
    return date;
}