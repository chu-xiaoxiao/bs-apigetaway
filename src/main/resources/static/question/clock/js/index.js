var canvas = document.getElementById("canvas");
		var ctx = canvas.getContext("2d");
		var suofang = 200/500;
		ctx.strokeStyle = '#000000';
		ctx.shadowBlur= 15*suofang;
		ctx.shadowColor = '#000000'

		function degToRad(degree){
			var factor = Math.PI/180;
			return degree*factor;
		}

		function renderTime(){
			var now = new Date();
			var today = now.toDateString();
			var time = now.toLocaleTimeString();
			var hrs = now.getHours();
			var min = now.getMinutes();
			var sec = now.getSeconds();
			var mil = now.getMilliseconds();
			var smoothsec = sec+(mil/1000);

      var smoothmin = min+(smoothsec/60);

			//Background
			gradient = ctx.createRadialGradient(250, 250, 5, 250, 250, 300);
			gradient.addColorStop(0, "#e6e6e6");
            gradient.addColorStop(1, "#e6e6e6");
			ctx.fillStyle = "#f8f8f8";
			//ctx.fillStyle = 'rgba(00 ,00 , 00, 1)';
			ctx.fillRect(0, 0, 500*suofang, 500*suofang);
			//Hours
            ctx.lineWidth = 11*suofang;
			ctx.beginPath();
			ctx.arc(250*suofang,250*suofang,200*suofang, degToRad(270), degToRad((hrs*30)-90));
			ctx.stroke();
			//Minutes
			ctx.beginPath();
            ctx.lineWidth = 8*suofang;
			ctx.arc(250*suofang,250*suofang,170*suofang, degToRad(270), degToRad((smoothmin*6)-90));
			ctx.stroke();
			//Seconds
			ctx.beginPath();
            ctx.lineWidth = 5*suofang;
			ctx.arc(250*suofang,250*suofang,140*suofang, degToRad(270), degToRad((smoothsec*6)-90));
			ctx.stroke();
			//Date
			ctx.font = 25*suofang+"px Helvetica";
			ctx.fillStyle = 'rgba(00, 00, 00, 1)';
			ctx.fillText(today, 175*suofang-40*suofang, 250*suofang);
			//Time
			ctx.font = 25*suofang+"px Helvetica Bold";
			ctx.fillStyle = 'rgba(00, 00, 00, 1)';
			ctx.fillText(time, 175*suofang-40*suofang, 280*suofang);

		}
		setInterval(renderTime, 40);