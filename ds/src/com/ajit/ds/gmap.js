	var gmap;
	var latLong;
	var oms;
	
	var data ;
	var myRedLatlng;
	var myGreenLatlng;

	var infowindow;
	var markerArr;
	var markerMulArr;
	var markerSingleArr;
	var newterminalDetail ;
	var markerArrForPos;
	var singlePosDataArray;
	  
	var myWidth;
	var myHeight;
	
	var userMonitorLicensed = false;
	
	var terminalDetailArray;
	var apiLoaded = false;
	
	var clientKeyStr = "";
	var macAddressStr = "";
	
	var isKeyInvalid = false;
	var invalidGmapKeyString = "The provided key is not a valid Google API Key";
	
	var currentinfowindowSingle = null; 
	var currentinfowindowMultiple = null;

	var terminalUnknownIcon;
	var terminalNotSupportedIcon;
	var terminalNormalIcon;
	var terminalMinorIcon;
	var terminalMajorIcon;
	var terminalCriticalIcon;
	var terminalRespondingIcon;
	var terminalUnmanagedIcon;
	var terminalDisabledIcon;
	var terminalWarningIcon;
	var terminalNoneIcon;
	var pushPinIcon;
	var icon;

    
    
	/**
	 * on load function for the wrapper html. Hides the google map div element.
	 * @return
	 */
	function load() {
		document.getElementById("googlemapPortion").style.visibility = 'hidden';
		var myAlert = alert;
		//this kills the initial alert thrown by map api if the key is invalid.
	    /*alert = function(a) { 
	    				if(a.indexOf(invalidGmapKeyString) != -1){isKeyInvalid = true;}
	    				else{myAlert(a);}
	    		};*/
	}

	/**
	 * This function initializes the height and width variables from Browser size.
	 * @return
	 */
	function initializeHeightWidth() {
		if( typeof( window.innerWidth ) == 'number' ) { 
			//Non-IE 
			myWidth = window.innerWidth;
			myHeight = window.innerHeight; 
	
		} else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) { 
			//IE 6+ in 'standards compliant mode' 
			myWidth = document.documentElement.clientWidth; 
			myHeight = document.documentElement.clientHeight; 
	
		} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) { 
			//IE 4 compatible 
			myWidth = document.body.clientWidth; 
			myHeight = document.body.clientHeight; 
	
		}
	}


	/**
	 * This function sets the Google map and search box size.
	 * @return
	 */
	function setGMapAndSearchBoxSize() {
		initializeHeightWidth();
		
		document.getElementById("googlemapPortion").style.height = myHeight+'px';
		document.getElementById("googlemapPortion").style.width = myWidth+'px';

		document.getElementById("googlemapForm").style.height = 50+'px';
		document.getElementById("googlemapForm").style.width = myWidth-5+'px';

		document.getElementById("googlemap").style.height = myHeight-53+'px';
		document.getElementById("googlemap").style.width = myWidth-5+'px';

	}
	
	/**
	 * This function shows the google map element visible and thus making the google map visible.
	 * @return
	 */
	function showGoogleMap() {
		setGMapAndSearchBoxSize();
		document.getElementById("googlemapPortion").style.visibility = 'visible';
		document.title = "Vision Web Client";
	}

	/**
	 * This function hides the google map element hidden.
	 * @return
	 */
	function hideGoogleMap() {
		document.getElementById("googlemapPortion").style.visibility = 'hidden';
		document.title = "Vision Web Client";
		infowindow.close();
	}
		
	/**
	 * This function resizes the google map div element according to passed browser size params.
	 * @return
	 */
	function resizeMap(width, height) {
		document.getElementById("googlemapPortion").style.width = width;
		document.getElementById("googlemapPortion").style.height = height;
		document.title = "Vision Web Client";
	}

	/**
	 * This function is called from the flex, on clicking on the globe image.
	 * It initializes the Vsat information and calls the google map api functions
	 * to show the google map.
	 * @param terminalsArray
	 * @param isUserLicensed
	 * @return
	 */
	function loadMap( terminalsArray, isUserLicensed) {
		try{
			 
			if(isKeyInvalid){
				 alert("Showing Low resolution map.");
				 VisionWeb.showOfflineMap();
				 return;
			}
			
			
			//check if api is loaded or not.
			if(!apiLoaded){
				//try to load the api
				if( (clientKeyStr.length > 0) && (macAddressStr.length > 0) ){
					addScriptTag(clientKeyStr, macAddressStr);
				}
				VisionWeb.showOfflineMap();
				alert("Showing Low resolution map");
				return;
			}
			
			//check the google map connectivity
			try{
				testCenter: new google.maps.LatLng(38.4767,-82.6397);
			}catch(error){
				VisionWeb.showOfflineMap();
				alert("Showing Low resolution map");
				return;
			}
			
			document.getElementById("searchValue").value = "";
			userMonitorLicensed = isUserLicensed;
			terminalDetailArray = terminalsArray;
			
			terminalUnknownIcon = new google.maps.MarkerImage("assets/status_null.png", null, null, null, new google.maps.Size(17, 17));
			terminalNotSupportedIcon = new google.maps.MarkerImage("assets/status_unknown.png", null, null, null, new google.maps.Size(17, 17));
			terminalNormalIcon = new google.maps.MarkerImage("assets/status_normal.jpg", null, null, null, new google.maps.Size(17, 17));
			terminalMinorIcon = new google.maps.MarkerImage("assets/status_minor.jpg", null, null, null, new google.maps.Size(17, 17));
			terminalMajorIcon = new google.maps.MarkerImage("assets/status_major.jpg", null, null, null, new google.maps.Size(17, 17));
			terminalCriticalIcon = new google.maps.MarkerImage("assets/status_critical.jpg", null, null, null, new google.maps.Size(17, 17));
			terminalRespondingIcon = new google.maps.MarkerImage("assets/status_critical.jpg", null, null, null, new google.maps.Size(17, 17));
			terminalUnmanagedIcon = new google.maps.MarkerImage("assets/status_unmanaged.png", null, null, null, new google.maps.Size(17, 17));
			terminalDisabledIcon = new google.maps.MarkerImage("assets/status_disabled.png", null, null, null, new google.maps.Size(17, 17));
			terminalWarningIcon = new google.maps.MarkerImage("assets/status_warning.png", null, null, null, new google.maps.Size(17, 17));
			terminalNoneIcon = new google.maps.MarkerImage("assets/status_null.png", null, null, null, new google.maps.Size(17, 17));
			pushPinIcon = new google.maps.MarkerImage("assets/pushpin.png", null, null, null, new google.maps.Size(17, 17));

			//create the data array for Single VSAT on a lat long  and Multiple vsat on same lat long
	        singlePosDataArray = new Array(); // Array which contains the detail of Single VSAT on a lat long 
	        mainArrForMultiplePos = new Array(); // Array which contains the details of Multiple vsat on same lat long, lat long and icon info
				        
	        for (var x = 0; x < terminalsArray.length; x++) {
		        var count=0;
		        multiplePosDataArray = new Array()//Array which contains the details of Multiple vsat on same lat long
		        var currentTerminalInfo = terminalsArray[x];
			    for (var j=x+1;j<terminalsArray.length;j++){
				    if(currentTerminalInfo.latitude !=0 && currentTerminalInfo.longitude !=0 ){
				        var checkWithTerminalsArray =terminalsArray[j];
				        if(checkWithTerminalsArray.latitude!=0 && checkWithTerminalsArray.longitude!=0 ){
					        if((checkWithTerminalsArray.latitude == currentTerminalInfo.latitude) &&
						        (checkWithTerminalsArray.longitude == currentTerminalInfo.longitude)){
					        		//When no VSAT detail is present into the the array
							        if(multiplePosDataArray.length==0){
							        	if(checkWithTerminalsArray.status == "UNKNOWN"){
							        		multiplePosDataArray.push({
										        deviceId: checkWithTerminalsArray.deviceId, 
										        lon: checkWithTerminalsArray.longitude,
										        lat: checkWithTerminalsArray.latitude,
										        detail: 
										        '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
										        ' ' + '<td> <img src="'+terminalUnknownIcon+'"/> </td>' +
										        '<td><input type="submit" value="Show Details" ' + 
										        'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
										        desc: 
										        "Vsat Name: " + checkWithTerminalsArray.name
									        });
									    }
							        	else if(checkWithTerminalsArray.status == "NOT_SUPPORTED"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalNotSupportedIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "NORMAL"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalNormalIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "MINOR"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalMinorIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "MAJOR"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalMajorIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "CRITICAL"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalCriticalIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "NOT_RESPONDING"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalRespondingIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "UNMANAGED"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalUnmanagedIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "DISABLED"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalDisabledIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "WARNING"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalWarningIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "NONE"){
											multiplePosDataArray.push({
												deviceId: checkWithTerminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalNoneIcon
											});
										}
							        	else if(checkWithTerminalsArray.status == "NCC2_UNREACHABLE"){
											multiplePosDataArray.push({
												deviceId: checkwithterminalsArray.deviceId,	
												lon: checkWithTerminalsArray.longitude,
												lat: checkWithTerminalsArray.latitude,
												detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
													'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
													'<td><input type="submit" value="Show Details" ' + 
													'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
												desc: "Vsat Name: " + checkWithTerminalsArray.name,
												icon: terminalNotSupportedIcon
											});
										}
							       	}
						        	//If entries are already present into the array
						        	else{
						        		//check if the detail for the vsat is already present in the array
						        		for(var ab=0;ab<multiplePosDataArray.length;ab++){
						        			var checkWithDataArr =multiplePosDataArray[ab];
						        			var flag =0;
							        		if(checkWithTerminalsArray.deviceId==checkWithDataArr.deviceId){
							        			flag =1;
							        		}
							        	}
						        		//Only if the details of the vsat is not present in the array then insert
						        		if(flag==0){
	
								        	if(checkWithTerminalsArray.status == "UNKNOWN"){
								        		multiplePosDataArray.push({
										        deviceId: checkWithTerminalsArray.deviceId, 
										        lon: checkWithTerminalsArray.longitude,
										        lat: checkWithTerminalsArray.latitude,
										        detail: 
										        '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
										        ' ' + '<td> <img src="'+terminalUnknownIcon+'"/> </td>' +
										        '<td><input type="submit" value="Show Details" ' + 
										        'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
										        desc: 
										        "Vsat Name: " + checkWithTerminalsArray.name
										        
										        });
										        }
								        	if(checkWithTerminalsArray.status == "NOT_SUPPORTED"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalNotSupportedIcon
												});
											}
											if(checkWithTerminalsArray.status == "NORMAL"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalNormalIcon
												});
											}
											if(checkWithTerminalsArray.status == "MINOR"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalMinorIcon
												});
											}
											if(checkWithTerminalsArray.status == "MAJOR"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalMajorIcon
												});
											}
											if(checkWithTerminalsArray.status == "CRITICAL"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalCriticalIcon
												});
											}
											if(checkWithTerminalsArray.status == "NOT_RESPONDING"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalRespondingIcon
												});
											}
											if(checkWithTerminalsArray.status == "UNMANAGED"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalUnmanagedIcon
												});
											}
											if(checkWithTerminalsArray.status == "DISABLED"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalDisabledIcon
												});
											}
											if(checkWithTerminalsArray.status == "WARNING"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalWarningIcon
												});
											}
											if(checkWithTerminalsArray.status == "NONE"){
												multiplePosDataArray.push({
													deviceId: checkWithTerminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name,
													icon: terminalNoneIcon
												});
											}
											if(checkWithTerminalsArray.status == "NCC2_UNREACHABLE"){
												multiplePosDataArray.push({
													deviceId: checkwithterminalsArray.deviceId,	
													lon: checkWithTerminalsArray.longitude,
													lat: checkWithTerminalsArray.latitude,
													detail:   '<tr><td>' + checkWithTerminalsArray.name + '</td>' + 
														'  ' + '<td>'+checkWithTerminalsArray.status+'</td>' +
														'<td><input type="submit" value="Show Details" ' + 
														'onclick="showVsatDetails(\''+ checkWithTerminalsArray.name +'\', \''+ checkWithTerminalsArray.deviceId +'\')" /> </td> </tr>',
													desc: "Vsat Name: " + checkWithTerminalsArray.name
												});
											}
								       	
						        		}
						        	}
					        	
						           count++;
						        }
					        } 
				        }
			        }
			        if(count>0){
			        	for(var abc=0;abc<multiplePosDataArray.length;abc++){
		        			var checkWithDataArray =multiplePosDataArray[abc];
		        			var flagTest =0;
			        		if(checkWithTerminalsArray.deviceId==checkWithDataArray.deviceId){
			        			flagTest =1;
			        		}
			        	}
		        		if(flagTest==0){
				        	multiplePosDataArray.push({
								deviceId: currentTerminalInfo.deviceId,	
								lon: currentTerminalInfo.longitude,
								lat: currentTerminalInfo.latitude,
								detail:   '<tr><td>' + currentTerminalInfo.name + '</td>' + 
									'  ' + '<td>'+currentTerminalInfo.status+'</td>' +
									'<td><input type="submit" value="Show Details" ' + 
									'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
								desc: "Vsat Name: " + currentTerminalInfo.name,
								icon: terminalNotSupportedIcon
							});
		        		}
				        if(mainArrForMultiplePos.length==0){
					        mainArrForMultiplePos.push({
						        longi: currentTerminalInfo.longitude,
						        lati:currentTerminalInfo.latitude,
						        detail:multiplePosDataArray,
						        icon:pushPinIcon
					        });
				        }
		
				        else{
				        	var flagCheck=0;
					        for(var k=0;k<mainArrForMultiplePos.length;k++){
						        var testmarkerArrForPos=mainArrForMultiplePos[k];
						        if((currentTerminalInfo.latitude==testmarkerArrForPos.lati)&& (currentTerminalInfo.longitude==testmarkerArrForPos.longi )){
						        	flagCheck=1;
						        }
						    }
						    if(flagCheck==0){
						        mainArrForMultiplePos.push({
							        longi: currentTerminalInfo.longitude,
							        lati:currentTerminalInfo.latitude,
							        detail:multiplePosDataArray,
							        icon:pushPinIcon
						        });
					        }
				        }
			        } 
			        else{
			        	if(currentTerminalInfo.latitude !=0 && currentTerminalInfo.longitude !=0 ){
				        //Keep the different Icon as per the status of the Vsat.
				        if(currentTerminalInfo.status == "UNKNOWN"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalUnknownIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalUnknownIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "NOT_SUPPORTED"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalNotSupportedIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon: terminalNotSupportedIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "NORMAL"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalNormalIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon: terminalNormalIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "MINOR"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalMinorIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalMinorIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "MAJOR"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalMajorIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalMajorIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "CRITICAL"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalCriticalIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalCriticalIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "NOT_RESPONDING"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalRespondingIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalRespondingIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "UNMANAGED"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalUnmanagedIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalUnmanagedIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "DISABLED"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalDisabledIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalDisabledIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "WARNING"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalWarningIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalWarningIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "NONE"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalNoneIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalNoneIcon
					        });
				        }
				        else if(currentTerminalInfo.status == "NCC2_UNREACHABLE"){
					        singlePosDataArray.push({
					        deviceId: currentTerminalInfo.deviceId, 
					        lon: currentTerminalInfo.longitude,
					        lat: currentTerminalInfo.latitude,
					        detail: 
					        '<tr><td>' + currentTerminalInfo.name + '</td>' + 
					        ' ' + '<td> <img src="'+terminalNotSupportedIcon+'"/> </td>' +
					        '<td><input type="submit" value="Show Details" ' + 
					        'onclick="showVsatDetails(\''+ currentTerminalInfo.name +'\', \''+ currentTerminalInfo.deviceId +'\')" /> </td> </tr>',
					        desc: 
					        "Vsat Name: " + currentTerminalInfo.name,
					        icon:terminalNotSupportedIcon
					        });
				        }
				    }
		        }
	        }       
		        
				       
		        
		      //create map options.
				var myOptions = {
		          center: new google.maps.LatLng(38.4767,-82.6397),
		          zoom: 3,
		          mapTypeId: google.maps.MapTypeId.ROADMAP
		        };
		        gmap = new google.maps.Map(document.getElementById("googlemap"),
		            myOptions);
		        
		        //alert("map created");
		        //alert("object"+mainArrForMultiplePos[0]);
		        //alert("lat"+mainArrForMultiplePos[0].lati+"long="+mainArrForMultiplePos[0].longi);
		        
		       
		       /* var marker = new google.maps.Marker( {
					position :loc,
					map :gmap,
					icon :newterminalDetail.icon
				// shadow: shadow
						});
				marker.desc = newterminalDetail.detail;	
				markerArr.push( {
					deviceId :currentTerminal.deviceId,
					markerVal :marker
				});	*/
		        
		        
	       //Add pushpin for the multiple vsat on the same lat long
			        if(mainArrForMultiplePos!=null){
			        	//alert("mainArrForMultiplePos.length="+mainArrForMultiplePos.length);
				        for (var z=0;z<mainArrForMultiplePos.length;z++){
				        	//alert("putting markers for multiple vsat");
				        	var datumMul = mainArrForMultiplePos[z];
				        	var locMul = new google.maps.LatLng(datumMul.lati, datumMul.longi);
				        	var markerMul = new google.maps.Marker({
					            position: locMul,
					            map: gmap,
					            icon: datumMul.icon
					        });
				        	//TODO:iterate over the array contained in datumMul and create the detail string to show the table.
				        	//multpleVsatDetail=..........
				        	markerMulArr.push({
				        		markerVal :markerMul,
				        		detail:multpleVsatDetail//TODO:created at above todo part
				        	});
				        }
			        }
		   
		        
		       //Add icon for the single vsat on the lat long
			          
			    if(singlePosDataArray!=null){
			        for (var i = 0; i < singlePosDataArray.length; i ++) {
			        	alert("putting markers for single vsat"+i);
			        	var datumSingle = singlePosDataArray[i];
			        	var locSingle = new google.maps.LatLng(datumSingle.lat, datumSingle.lon);
				  		var markerSingle = new google.maps.Marker({
				            position: locSingle,
				            map: gmap,
				            icon: datumSingle.icon
				        });
				  		
				  		markerSingleArr.push({
			        		markerVal :markerMul,
			        		detail:datumSingle.detail
			        	});
			        }
			    } 
		        
		      
		     var contentString = 'Vsat Info';
		    	
				//create info window and add them to markers.
				infowindow = new google.maps.InfoWindow({
				    content: contentString
				});
		     
				 
				//Showing the info Window for launching the Vsat in detail mode.
		        for (var y = 0; y < markerSingleArr.length; y ++) {
		        currentinfowindowSingle=null;
		        	var singleVsat = markerSingleArr[y];
		        	 google.maps.event.addListener(singleVsat, 'mouseover', function() {
		        		    infowindow.setPosition(singleVsat.lon,singleVsat.lat);
							infowindow.setContent('<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + singleVsat.detail + '</table>');
							infowindow.open(gmap,this); 
		        	 });
		        	 google.maps.event.addListener(singleVsat, 'mouseout', function() {
					if(currentinfowindowSingle==null)
					{infowindow.close(gmap,this);}
				});
		        
				google.maps.event.addListener(singleVsat, 'click', function() {
		        		    infowindow.setPosition(singleVsat.lon,singleVsat.lat);
							infowindow.setContent('<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + singleVsat.detail + '</table>');
							if(currentinfowindowSingle=="clicked")
							infowindow.open(gmap,this); 
		        	 });
		        }   
		      //create info window and add them to markers.
				infowindowMul = new google.maps.InfoWindow({
				    content: contentString
				});
		        
				try{
		        //Showing the info window for launching multiple Vsat in detail mode
		        for(var l=0;l<markerMulArr.length;l++){
		           currentinfowindowMultiple = null;
		        	//var finalDescString='';
		        	//var detailArr = new Array();
		        	var multipleVsat =markerMulArr[l];
		        	//alert("multipleVsat="+multipleVsat);
		        	detailArr = multipleVsat.detail;
		        	     /*for (var n=0;n<detailArr.length;n++){
		        	    	 var multiArr=detailArr[n];
		        	    	 alert(n+ " multiarr:" +multiArr);
		        	    	 if (multiArr != null) {
		        	    		 finalDescString = finalDescString+ multiArr.detail;
		        	    		 alert(n+ " finalDescString:" +finalDescString);
		        	    	 }
		        	     }*/
		        	 google.maps.event.addListener(multipleVsat, 'mouseover', function() {
		        		 //finalDescString = '<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + finalDescString + '</table>';
		        		    infowindowMul.setPosition(multipleVsat.longi,multipleVsat.lati);
							infowindowMul.setContent(detailArr);
							infowindowMul.open(gmap,this); 
		        	 });
		        	 
		        	  google.maps.event.addListener(multipleVsat, 'mouseout', function() {
					if(currentinfowindowMultiple==null)
					{infowindow.close(gmap,this);}
				});
				 google.maps.event.addListener(multipleVsat, 'click', function() {
				         currentinfowindowMultiple="clicked";
		        		 //finalDescString = '<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + finalDescString + '</table>';
		        		    infowindowMul.setPosition(multipleVsat.longi,multipleVsat.lati);
							infowindowMul.setContent(detailArr);
							if(currentinfowindowMultiple=="clicked")
							infowindowMul.open(gmap,this); 
		        	 });
		        	 
		        }
				}
				catch(error){
					alert("sanjeev "+ error);
				}
				
		        
	        //Everything is ready show Advance map now.
	        showGoogleMap();
		}
		catch(error){
			alert("eeror"+error);
			VisionWeb.showOfflineMap();
			hideGoogleMap();
			alert("Showing Low resolution map");
			return;
		}
    } 

	/**
	 * Closes the google map.
	 */
	function closeGoogleMap(){
		hideGoogleMap();
	}
	
	
	/**
	 * If user has valid NMS Monitor key, then this function calls the flex function to
	 * show the selected Vsat Details. Else it shows the error message.
	 */
	function showVsatDetails(vsatName, deviceId){
		if(userMonitorLicensed){
			VisionWeb.showVsatDetails(vsatName, deviceId);
		}
		else{
			alert("Satellite router detail required Valid License Key");
		}
	}

	
	/**
	 * This function searches the Vsat by calling the other helping function according to
	 * the input criteria.
	 */
	function searchVsat(){
		//First check for empty value;
		var searchValue=document.getElementById("searchValue").value;
		var searchOption = document.getElementById("searchOption").value;
		if (searchValue==null || trim(searchValue)=="")
		{
		  	alert("Please enter the Value to be searched.");
		  	return;
		}
		searchValue = trim(searchValue);
		if ( (searchOption.indexOf("IP") != -1) )
		{
			if(!validateIp(searchValue)){
				alert("IP Address Format is incorrect");
				return;
			}else if(searchValue == "0.0.0.0"){
				alert("IP Address is default and can't be searched");
				return;
			}
		} 
		
		if (searchOption == "Serial Num")
   		{
   			searchBySerialNum(searchValue);
   		}
   		else if (searchOption == "LAN IP")
   		{
   			searchByLanIP(searchValue);
   		}
   		else if (searchOption == "NAT IP")
   		{
   			searchByNatIP(searchValue);
   		}
   		else if (searchOption == "Name")
   		{
   			searchByName(searchValue);
   		}
   		else if (searchOption == "Mgmt IP")
   		{
   			searchByMgmtIP(searchValue);
   		}
	}
	
	
	/**
	 * This function searches the Vsat by input Management IP
	 * 
	 */
	function searchByMgmtIP( searchText ){
		for (var i = 0; i < terminalDetailArray.length; i++) {
			var currentTerminal = terminalDetailArray[i];
			if( currentTerminal.mgtIp == searchText ){
				if(currentTerminal.latitude == 0 && currentTerminal.longitude ==0 ){
					alert("The terminal exists but doesn't have lat, long information.");
					return;
				}
				else{
					zoomAndShowInfo(currentTerminal);
					return;
				}
			}
		}
		alert("The terminal doesn't exists.");
	}
	
	
	/**
	 * This function searches the Vsat by input Vsat Name
	 * 
	 */
	function searchByName( searchText ){
		for (var i = 0; i < terminalDetailArray.length; i++) {
			var currentTerminal = terminalDetailArray[i];
			if( currentTerminal.name.toLowerCase() == searchText.toLowerCase() ){
				if(currentTerminal.latitude == 0 && currentTerminal.longitude ==0 ){
					alert("The terminal exists but doesn't have lat, long information.");
					return;
				}
				else{
					zoomAndShowInfo(currentTerminal);
					return;
				}
			}
		}
		alert("The terminal doesn't exists.");
	}
	
	
	/**
	 * This function searches the Vsat by input NAT IP
	 * 
	 */
	function searchByNatIP( searchText ){
		for (var i = 0; i < terminalDetailArray.length; i++) {
			var currentTerminal = terminalDetailArray[i];
			if( (currentTerminal.nat1Ip == searchText) || (currentTerminal.nat2Ip == searchText) ){
				if(currentTerminal.latitude == 0 && currentTerminal.longitude ==0 ){
					alert("The terminal exists but doesn't have lat, long information.");
					return;
				}
				else{
					zoomAndShowInfo(currentTerminal);
					return;
				}
			}
		}
		alert("The terminal doesn't exists.");
	}
	
	/**
	 * This function searches the Vsat by input LAN IP
	 * 
	 */
	function searchByLanIP( searchText ){
		for (var i = 0; i < terminalDetailArray.length; i++) {
			var currentTerminal = terminalDetailArray[i];
			if( (currentTerminal.lan1Ip == searchText) || (currentTerminal.lan2Ip == searchText) ){
				if(currentTerminal.latitude == 0 && currentTerminal.longitude ==0 ){
					alert("The terminal exists but doesn't have lat, long information.");
					return;
				}
				else{
					zoomAndShowInfo(currentTerminal);
					return;
				}
			}
		}
		alert("The terminal doesn't exists.");
	}
	
	/**
	 * This function searches the Vsat by input serial number
	 * 
	 */
	function searchBySerialNum( searchText ){
		for (var i = 0; i < terminalDetailArray.length; i++) {
			var currentTerminal = terminalDetailArray[i];
			if(currentTerminal.serialNumber == searchText){
				if(currentTerminal.latitude == 0 && currentTerminal.longitude ==0 ){
					alert("The terminal exists but doesn't have lat, long information.");
					return;
				}
				else{
					zoomAndShowInfo(currentTerminal);
					return;
				}
			}
		}
		alert("The terminal doesn't exists.");
	}
	
	/**
	 * This function zooms at the input terminal location and opens info window to show the information.
	 * 
	 */
	function zoomAndShowInfo(currentTerminal){
		//Zoom to the location on the Google Map
		latLong = new google.maps.LatLng(currentTerminal.latitude, currentTerminal.longitude);
		gmap.setCenter(latLong);
		gmap.setZoom(7);
		
		//close existing infowindow
		infowindow.close();
		//Show info Window there
		infowindow = new google.maps.InfoWindow({
		    content: '<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr><tr><td>' + currentTerminal.name + '</td>' + 
							'  ' + '<td>'+currentTerminal.status+'</td>' +
							'<td><input type="submit" value="Show Details" ' + 
							'onclick="showVsatDetails(\''+ currentTerminal.name +'\', \''+ currentTerminal.deviceId +'\')" /> </td> </tr></table>',
		    position: latLong
		});
		infowindow.open(gmap);
	}

	/**
	 * This function validated a string for the IPv4 address format. 
	 * 
	 */
	function validateIp(IPText){          
	
		RegE = /^([1]?\d{1,2}|2[0-4]{1}\d{1}|25[0-5]{1})(\.([1]?\d{1,2}|2[0-4]{1}\d{1}|25[0-5]{1})){3}$/;  
		if(IPText.match(RegE))  
		     return true;
		    
		else  
		    return false;
	}

	
	/**
	 * This function adds the src tag for google map api's dynamically. 
	 * 
	 */
	function addScriptTag(clientKey, macAdd){
		clientKeyStr = clientKey;
		macAddressStr = macAdd;
		if(!apiLoaded){
			//TODO: create the api src using the clientKey and macAdd and use that as source
			//apiSource = 'http://maps.googleapis.com/maps/api/js?v=3&client='+clientKey+'&sensor=false&channel='+macAdd+'&callback=loadMaps';
			apiSource = 'http://maps.googleapis.com/maps/api/js?v=3&key='+clientKey+'&sensor=false&channel='+macAdd+'&callback=loadMaps';
			var head = document.getElementsByTagName('head')[0];
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.id = 'advMapSrc';
			script.src = apiSource;
			head.appendChild(script);
		}
	}
	
	/**
	 * Callback function for the successful loading of gmap api 
	 * 
	 */
	function loadMaps(){
		apiLoaded = true;
	}
	
	/**
	 * This functions trims space characters on the left and right of string.
	 * @param s
	 * @return space trimmmed string
	 */
	function trim(s)
	{
		var l=0; var r=s.length -1;
		while(l < s.length && s[l] == ' ')
		{	l++; }
		while(r > l && s[r] == ' ')
		{	r-=1;	}
		return s.substring(l, r+1);
	}

/*	*//**
 * This method updates the VSAT Status
 * @param deviceId
 * @param deviceStatus
 * @return
 *
 *//*
function updateDeviceStatus(deviceId, deviceStatus) {
	var currentTerminalId = deviceId;
	var newTerminalStatus = deviceStatus;
	
	for ( var i = 0; i < terminalDetailArray.length; i++) {
		
		var currentTerminal = terminalDetailArray[i];
	
		if (currentTerminal.deviceId == currentTerminalId) {
	
			currentTerminal.status = newTerminalStatus;
		
			if (currentTerminal.status == "UNKNOWN") {
				newterminalDetail = 
						{
							icon :terminalUnknownIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "NOT_SUPPORTED") {
				newterminalDetail =
						 {
							icon :terminalNotSupportedIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "NORMAL") {
				newterminalDetail =
						 {
							icon :terminalNormalIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "MINOR") {
				newterminalDetail =
						 {
							icon :terminalMinorIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "MAJOR") {
				newterminalDetail =
						 {
							icon :terminalMajorIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "CRITICAL") {
				newterminalDetail =
						 {
							icon :terminalCriticalIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "NOT_RESPONDING") {
				newterminalDetail =
						 {
							icon :terminalRespondingIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "UNMANAGED") {
				newterminalDetail =
						 {
							icon :terminalUnmanagedIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "DISABLED") {
				newterminalDetail = 
						 {
							icon :terminalDisabledIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "WARNING") {
				newterminalDetail =
						 {
							icon :terminalWarningIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
								
						};
			}
			if (currentTerminal.status == "NONE") {
				newterminalDetail =
						 {
							icon :terminalNoneIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			if (currentTerminal.status == "NCC2_UNREACHABLE") {
				newterminalDetail =
						 {
							icon :terminalNotSupportedIcon,
							detail :'<tr><td>'
								+ currentTerminal.name
								+ '</td>'
								+ '  '
								+ '<td>'
								+ currentTerminal.status
								+ '</td>'
								+ '<td><input type="submit" value="Show Details" '
								+ 'onclick="showVsatDetails(\''
								+ currentTerminal.name + '\', \''
								+ currentTerminal.deviceId
								+ '\')" /> </td> </tr>'
						};
			}
			
			var loc = new google.maps.LatLng(currentTerminal.latitude, currentTerminal.longitude);

			for ( var j = 0; j < markerArr.length; j++) {
		
				var icondeviceId =  markerArr[j].deviceId;
				
				if(icondeviceId == currentTerminal.deviceId)
				{					
						markerArr[j].markerVal.setMap(null);
						markerArr.splice(j, 1);	
					
				}
			}
			
			var marker = new google.maps.Marker( {
				position :loc,
				map :gmap,
				icon :newterminalDetail.icon
			// shadow: shadow
					});
			marker.desc = newterminalDetail.detail;	
			markerArr.push( {
				deviceId :currentTerminal.deviceId,
				markerVal :marker
			});	
	
			// Showing the info Window for launching the Vsat in detail mode.
				
				var markerLocal = marker;
		
				google.maps.event.addListener(markerLocal, 'mouseover', function() {
                							currentinfowindow=null;


									// First check if there is any other vsat here
									// with the same Lat, Long.
									
									var markerPosition = this.getPosition();
									var markerDesc = this.desc;
									var finalDescString = markerDesc;
									var multipleMarkerExists = false;
									for ( var k = 0; k < markerArr.length; k++) {
										var markerInner = markerArr[k].markerVal;
										if ((markerInner.getPosition().lat() == markerPosition
												.lat())
												&& (markerInner.getPosition().lng() == markerPosition
														.lng())
												&& (markerInner.desc != markerDesc)) {
											// This marker is different from the
											// clicked
											// marker but has same Lag, long. Its
											// contents
											// should also be shown on clicking the
											// marker.
											finalDescString = finalDescString
													+ markerInner.desc;
											multipleMarkerExists = true;
										}
									}

									if (multipleMarkerExists) {
										finalDescString = '<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + finalDescString + '</table>';
										infowindow.setPosition(this.getPosition());
										infowindow.setContent(finalDescString);
										infowindow.open(gmap, this);
									} else {
										infowindow.setPosition(this.getPosition());
										infowindow
												.setContent('<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + this.desc + '</table>');
										infowindow.open(gmap, this);
									}

								});
								google.maps.event.addListener(markerLocal, 'mouseout', function() {
					if(currentinfowindow==null)
					{infowindow.close(gmap,this);}
				});
				
           		 google.maps.event.addListener(markerLocal, 'click', function() {
            							currentinfowindow="clicked";
									//First check if there is any other vsat here with the same Lat, Long.
									var markerPosition = this.getPosition();
									var markerDesc = this.desc;
									var finalDescString = markerDesc;
									var multipleMarkerExists = false;
									for (var j = 0; j < markerArr.length; j ++) {
									var markerInner = markerArr[j].markerVal;
									if( (markerInner.getPosition().lat() == markerPosition.lat()) &&
										(markerInner.getPosition().lng() == markerPosition.lng()) && 
										(markerInner.desc != markerDesc) ){
											//This marker is different from the clicked marker but has same Lag, long. Its contents
											//should also be shown on clicking the marker.
											finalDescString = finalDescString + markerInner.desc;
											multipleMarkerExists = true;
									}
								}	
					
								if(multipleMarkerExists){
									finalDescString = '<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + finalDescString + '</table>';
									infowindow.setPosition(this.getPosition());
									infowindow.setContent(finalDescString);
										if(currentinfowindow=="clicked")
										{infowindow.open(gmap,this);}
								}else{
									infowindow.setPosition(this.getPosition());
									infowindow.setContent('<table border=1 class=gmapTable><tr><th>Name</th><th>Status</th><th>Detail</th></tr>' + this.desc + '</table>');
									if(currentinfowindow=="clicked")
									infowindow.open(gmap,this);
								}
					
								});
		}
		
	}
}*/