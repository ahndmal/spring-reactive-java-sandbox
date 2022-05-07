
function log(msg) {
    let msgDiv = document.querySelector('#messages');
    let elem = document.createElement('div');
    let txt = document.createTextNode(msg);
    elem.appendChild(txt);
    msgDiv.appendChild(elem);
}

window.addEventListener('load', evt => {
   log('window has loaded');
   let eventSource = new EventSource('http://localhost:8080/sse/10');
   eventSource.addEventListener('message', e => {
       e.preventDefault();
       log(e.data);
   })
    eventSource.addEventListener('error', e => {
        e.preventDefault();
        log('Closing the Event Source');
        eventSource.close();
    })
});