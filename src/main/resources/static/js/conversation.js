'use strict';

let localStream = null;
let peer = null;
let existingCall = null;

navigator.mediaDevices.getUserMedia({video: false, audio: true})
    .then(function (stream) {
        // Success
        // $('#my-video').get(0).srcObject = stream;
        localStream = stream;
        updateAudioEnable(false)
    }).catch(function (error) {
    // Error
    console.error('mediaDevice.getUserMedia() error:', error);
    return;
});

peer = new Peer({
    key: '339d7027-558e-4bc8-a59a-64478447ce23',
    debug: 3
});

peer.on('open', function(){
    postRoomId(peer.id);
    $('#my-id').text(peer.id);
});

peer.on('error', function(err){
    alert(err.message);
});

peer.on('close', function(){
});

peer.on('disconnected', function(){
});

$('#make-call').submit(function(e){
    e.preventDefault();
    const call = peer.call($('#callto-id').val(), localStream);
    setupCallEventHandlers(call);
});

$('#end-call').click(function(){
    existingCall.close();
});

peer.on('call', function(call){
    call.answer(localStream);
    setupCallEventHandlers(call);
});

function setupCallEventHandlers(call){
    if (existingCall) {
        existingCall.close();
    };

    existingCall = call;

    call.on('stream', function(stream){
        addVideo(call,stream);
        setupEndCallUI();
        $('#their-id').text(call.remoteId);
    });
    call.on('close', function(){
        removeVideo(call.remoteId);
        setupMakeCallUI();
    });
}

function addVideo(call,stream){
    updateAudioEnable(true);
    $('#their-audio').get(0).srcObject = stream;
}

function removeVideo(peerId){
    updateAudioEnable(false);
    $('#'+peerId).remove();
}

function setupMakeCallUI(){
    $('#make-call').show();
    $('#end-call').hide();
}

function setupEndCallUI() {
    $('#make-call').hide();
    $('#end-call').show();
}

function updateAudioEnable(enable) {
    if (localStream) {
        localStream.getAudioTracks()[0].enabled = enable
    }
}

function postRoomId(id) {
    var url= 'http://localhost:8080/notify_room_name';
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({userId: userId, roomId: id}),
        timeout: 3000,
    }).done(function (data) {

    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {

    });

}