<script>
  var clientId = '894408373909-ehr1pd952hdus4injd8ri219rnhb5a0h.apps.googleusercontent.com';
  var developerKey = 'AIzaSyAeUmY6Ez2OiISdzU-5kfqXSIhMZEhXj30';
  var accessToken;

  function onApiLoad() {
    gapi.load('auth', authenticateWithGoogle);
    gapi.load('picker');  
  }
  function authenticateWithGoogle() {
    window.gapi.auth.authorize({
      'client_id': clientId,
      'scope': ['https://www.googleapis.com/auth/drive']
    }, handleAuthentication);
  }
  function handleAuthentication(result) {
    if(result && !result.error) {
      accessToken = result.access_token;
      setupPicker();
    }
  }
  function setupPicker() {
    var picker = new google.picker.PickerBuilder()
      .setOAuthToken(accessToken)
      .setDeveloperKey(developerKey)
      .addView(new google.picker.DocsUploadView())
      .enableFeature(google.picker.Feature.NAV_HIDDEN)
      .setCallback(myCallback)
      .build();
    picker.setVisible(true);
  }
  function myCallback(data) {
    if (data.action == google.picker.Action.PICKED) {
      alert(data.docs[0].name);
    }
    else if (data.action == google.picker.Action.CANCEL) {
      alert('goodbye');
    }
  }
</script>







