/*先base64加密再rsa加密*/
/*先base64加密再shal加密*/
function encryptStringBase64AndSHAL(inputString) {
    inputString = Base64.encode(inputString);
    inputString = hex_sha1(inputString);
    return inputString;
}