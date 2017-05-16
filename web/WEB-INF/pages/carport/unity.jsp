<%--
  Created by IntelliJ IDEA.
  User: AR Istvan Farkas
  Date: 5/16/2017
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>


<script>
    var gameInstance = UnityLoader.instantiate("gameContainer", "${assets}Build/Carport_FOG_002.json", {onProgress: UnityProgress});
</script>
<div class="webgl-content">
    <div id="gameContainer" style="width: 960px; height: 600px"></div>
    <div class="footer">
        <div class="webgl-logo"></div>
        <div class="fullscreen" onclick="gameInstance.SetFullscreen(1)"></div>
        <div class="title">Carport</div>
    </div>
</div>
