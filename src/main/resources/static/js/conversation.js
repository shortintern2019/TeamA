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
        connect()
    }).catch(function (error) {
    // Error
    console.error('mediaDevice.getUserMedia() error:', error);
    return;
});

function connect() {
    peer = new Peer({
        key: '339d7027-558e-4bc8-a59a-64478447ce23',
        debug: 3
    });

    peer.on('open', function(){
        postRoomId(peer.id);
        $('#my-id').text(peer.id);
        console.log(peer.id);

        if (roomName != "wait") {
            callPartner()
        }
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
}




function callPartner() {
    const call = peer.call(roomName, localStream, {
        metadata: {
            partnerUserName: userName,
            partnerUserId: userId
        }
    });
    setupCallEventHandlers(call);
}

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
    $('#review-partner-user-name').text(partnerUserName + "さんの評価をよろしくお願い致します。");
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

    if(roomName == "wait") {
        // var tmp = $('#my-id').val();
        $("[name=roomName]").val(peer.id);
    } else {
        $("[name=roomName]").val(roomName);
    }
    $("[name=role]").val(role);
    $('#review-form').submit();
});

function postRoomId(id) {
    var url= '/notify_room_name';
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