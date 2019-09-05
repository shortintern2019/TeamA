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

peer.on('call', function(call){
    call.answer(localStream);
    partnerUserName = call.metadata.partnerUserName;
    partnerUserId = call.metadata.partnerUserId;
    setupCallEventHandlers(call);
});

if (roomName != "wait") {
    setTimeout(callPartner, 1*1000);
}

function callPartner() {
    const call = peer.call(roomName, localStream, {
        metadata: {
            partnerUserName: partnerUserName,
            partnerUserId: partnerUserId
        }
    });
    setupCallEventHandlers(call);
}



$('#make-call').submit(function(e){
    e.preventDefault();
    const call = peer.call($('#callto-id').val(), localStream);
    setupCallEventHandlers(call);
});

$('#end-call').click(function(){
    existingCall.close();
});

function setupCallEventHandlers(call){
    if (existingCall) {
        return
    }

    call.on('close', function(){
        endPeer()
    });

    existingCall = call;

    endStandby();

    call.on('stream', function(stream){
        addAudio(call,stream);
        setupEndCallUI();
        $('#their-id').text(call.remoteId);
    });
    call.on('close', function(){
        removeAudio(call.remoteId);
        setupMakeCallUI();
    });
}

function addAudio(call, stream){
    updateAudioEnable(true);
    $('#their-audio').get(0).srcObject = stream;
}

function removeAudio(peerId){
    updateAudioEnable(false);
    // $('#'+peerId).remove();
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

function endStandby() {
    $('.standby-container').hide();
    $('.conversation-container').show();
    $('#partner-user-name').text(partnerUserName);
}

function endPeer() {
    // window.location.href = '/';
    $('.review-container').click()
}

$('.review-container').modaal({
    is_locked: true
});

$('#send-button').on('click', function() {

    if($("[name=ratingScore]:checked").length == 0) {
        alert("スコアを選択してください。");
        return
    }

    $("[name=userId]").val(partnerUserId);
    $('#review-form').submit();
});

function postRoomId(id) {
    var url= 'http://localhost:8080/notify_room_name';
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({"room": id, "role": role}),
        timeout: 3000,
    }).done(function (data) {
    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
    });
}