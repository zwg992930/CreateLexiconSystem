function index(current, obj)// 获取元素索引值
{
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i] == current)
			return i;
	}
}
window.onload = function() {
	
	var tab = document.getElementById('tab');
	var tab_a = tab.getElementsByClassName('manager');
	var tab_content = tab.getElementsByClassName('tab-content');
	for ( var i = 0; i < tab_a.length; i++) {
		tab_a[i].onclick = function() {
			show_tab_content(index(this, tab_a));
		};
	}
	function show_tab_content(i) {
		for ( var j = 0; j < tab_content.length; j++) {
			tab_a[j].style.borderBottom = "1px solid";
			tab_content[j].style.zIndex = 0;
		}
		tab_a[i].style.borderBottom = '0';
		tab_content[i].style.zIndex = 2;
	}
};