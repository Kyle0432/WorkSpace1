<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	$(function(){
		$("a").each(function(){
			this.onclick = function(){
				var serializeVal = $(":hidden").serialize();
				var href = this.href + "&" + serializeVal;
				window.location.href = href;			
				return false;
			};
		});
	});	
	
</script>
<!-- 为了把这两个条件都带上,这样带条件了点击下一页就不会返回原来,能在原先的基础上进行下一页或上一页等等 -->
<input type="hidden" name="minPrice" value="${param.minPrice }"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice }"/>
	<!-- <script type="text/javascript">
	
	$(function(){
		$("a").click(function(){
				var serializeVal = $(":hidden").serialize();
				var href = this.href + "&" + serializeVal;
				window.location.href = href;			
				return false;
		});
	});	
	
</script> -->