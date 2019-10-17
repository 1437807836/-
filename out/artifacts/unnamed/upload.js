

function initPra(mid_choose, mid_container, mfilename, mdsiplay,page) {

	var accessid = ''
	var accesskey = ''
	var host = ''
	var policyBase64 = ''
	var signature = ''
	var callbackbody = ''
	var filename = ''
	var key = ''
	var expire = 0
	var g_object_name = ''
	var g_object_name_type = ''
	var stuff = '.png';
	var now = timestamp = Date.parse(new Date()) / 1000;
	var obj;
	key_to(key_to_data);

	function key_to_data(data) {

		obj = data;
		host = obj['host']
		policyBase64 = obj['policy']
		accessid = obj['accessid']
		signature = obj['signature']
		expire = parseInt(obj['expire'])
		callbackbody = obj['callback']
		//      key = obj['dir']
	}

	function send_request() {
		var xmlhttp = null;
		if(window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else if(window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		if(xmlhttp != null) {
			console.log(obj);
			return obj;
		} else {
			alert("Your browser does not support XMLHTTP.");
		}
	};

	function check_object_radio() {
		var tt = document.getElementsByName('myradio');
		for(var i = 0; i < tt.length; i++) {
			if(tt[i].checked) {
				g_object_name_type = tt[i].value;
				break;
			}
		}
	}

	function get_signature() {
		//可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
		now = timestamp = Date.parse(new Date()) / 1000;
		if(expire < now + 3) {
			//      obj = send_request()//key_to_data
			key_to(key_to_data);
			//      var obj = eval ("(" + body + ")");
			//alert(obj['host'])
			//      host = obj['host']
			//      policyBase64 = obj['policy']
			//      accessid = obj['accessid']
			//      signature = obj['signature']
			//      expire = parseInt(obj['expire'])
			//      callbackbody = obj['callback'] 
			//      key = obj['dir']
			return true;
		}
		return false;
	};
	function random_string(len) {
		len = len || 32;
		var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
		var maxPos = chars.length;
		var pwd = '';
		for(i = 0; i < len; i++) {
			pwd += chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return pwd;
	}

	function get_suffix(filename) {
		pos = filename.lastIndexOf('.')
		suffix = ''
		if(pos != -1) {
			suffix = filename.substring(pos)
		}
		stuff = suffix;
		return suffix;
	}

	function calculate_object_name(filename, suff) {
		var randfile = ImageUploadPath();
		var mfilename = uploadfilename + randfile + suff;
		g_object_name = mfilename;

	}

	function set_upload_param(up, filename, ret) {
		if(ret == false) {
			ret = get_signature()
		}
		g_object_name = key;

		if(filename != '') {
			suffix = get_suffix(filename)
			calculate_object_name(filename, suffix)
		}
		new_multipart_params = {
			'key': g_object_name,
			'policy': policyBase64,
			'OSSAccessKeyId': accessid,
			'success_action_status': '200', //让服务端返回200,不然，默认会返回204
			'callback': callbackbody,
			'signature': signature,
		};

		up.setOption({
			'url': host,
			'multipart_params': new_multipart_params
		});

		up.start();
	}

	var id_choose = mid_choose;
	var id_display = mdsiplay;
	var uploadfilename = mfilename;
	var uploader = new plupload.Uploader({
		runtimes: 'html5,flash,silverlight,html4',
		browse_button: id_choose,
		//multi_selection: false,
		container: document.getElementById(mid_container),
		flash_swf_url: 'lib/plupload-2.1.2/js/Moxie.swf',
		silverlight_xap_url: 'lib/plupload-2.1.2/js/Moxie.xap',
		url: 'http://oss.aliyuncs.com',
		init: {
			PostInit: function() {

			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById(id_display).innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>' +
						'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>' +
						'</div>';
				});
				set_upload_param(uploader, '', false);
			},

			BeforeUpload: function(up, file) {
				check_object_radio();
				set_upload_param(up, file.name, true);
			},

			UploadProgress: function(up, file) {
				var d = document.getElementById(file.id);
				d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				var prog = d.getElementsByTagName('div')[0];
				var progBar = prog.getElementsByTagName('div')[0]
				progBar.style.width = 2 * file.percent + 'px';
				progBar.setAttribute('aria-valuenow', file.percent);
			},

			FileUploaded: function(up, file, info) {
				if(info.status == 200) {
					var path = host + '/' + g_object_name;
					localStorage.setItem(page,g_object_name);
					document.getElementById(id_choose).src = path;
				} else {
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
				}
			},

			Error: function(up, err) {
				if(err.code == -600) {
					document.getElementById('console').appendChild(document.createTextNode("\n选择的文件太大了,可以根据应用情况，在upload.js 设置一下上传的最大大小"));
				} else if(err.code == -601) {
					document.getElementById('console').appendChild(document.createTextNode("\n选择的文件后缀不对,可以根据应用情况，在upload.js进行设置可允许的上传文件类型"));
				} else if(err.code == -602) {
					document.getElementById('console').appendChild(document.createTextNode("\n这个文件已经上传过一遍了"));
				} else {
					document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
				}
			}
		}
	});

	uploader.init();
}