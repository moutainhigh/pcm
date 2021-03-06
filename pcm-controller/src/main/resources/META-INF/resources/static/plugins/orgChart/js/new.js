'use strict';

  var getdatasource = function(){

    //调用后台接口获得数据
    return {
      'name': 'Ball game',
      'content': 'general manager',
      'children':[
        {
          'name': 'Ball game',
          'content': 'general manager',
        },{
          'name': 'Ball game',
          'content': 'general manager',
        }
      ]
    }
  };

//获取id
  var getId = function() {
    return (new Date().getTime()) * 1000 + Math.floor(Math.random() * 1001);
  };

  var getdata = function(){
    if($('#chart-container').find('.node:first').length===0) return '';
    var hierarchy = $('#chart-container').orgchart('getHierarchy');
    if(hierarchy) {return hierarchy;}
    return '';
  }

  var addnode = function( name , content , cid , pid){
    var $chartContainer = $('#chart-container')
    var nodeVals = []
    var obj = {name: '',content: ''}
    obj.name = name ? name : ''
    obj.content = content ? content : ''
    obj.id = cid ? cid : ''
    nodeVals.push(obj)
    if (pid)var $node = $('#' + pid);     
    var hasChild = $node.parent().attr('colspan') > 0 ? true : false
    if (!hasChild) {
      var rel = nodeVals.length > 1 ? '110' : '100'
      $chartContainer.orgchart('addChildren', $node, {
        'children': nodeVals.map(function (item) {
          return { 'name': item.name,'content': item.content, 'relationship': rel, 'Id': item.id }
        })
      }, $.extend({}, $chartContainer.find('.orgchart').data('options'), { depth: 0 }))
    } else {
      $chartContainer.orgchart('addSiblings', $node.closest('tr').siblings('.nodes').find('.node:first'),
        { 'siblings': nodeVals.map(function (item) { return { 'name': item.name, 'content': item.content,'relationship': '110', 'Id': item.id }; })
        })
    }
   }

   var deletenode = function(pid){
    var $node = $('#' + pid)
    if ($node[0] === $('.orgchart').find('.node:first')[0]) {
      alert('不能删除根节点')
      return getdata();
    }
    $('#chart-container').orgchart('removeNodes', $node)
    $('#selected-node').val('').data('node', null)
    return getdata();
  }

var oc ;
//渲染组织树
  var renderpic = function(datascource){
    $('#chart-container').empty();//渲染之前先全部清空
    oc =  $('#chart-container').orgchart({
      'data' : datascource,
      'parentNodeSymbol': 'fa-th-large',
			'exportButton': true,
			'exportFilename': 'MyOrgChart',
      'nodeContent': "content",
      'createNode': function($node, data) {
		  
        $node[0].id = getId();
		
		//设置提示消息
		 var secondMenuIcon = $('<i>', {
	       'class': 'fa fa-info-circle second-menu-icon',
	       mouseover: function() {
	         $(this).siblings('.second-menu').toggle(); //toggle() 方法实现元素的不可见,如果元素为可见调味不可见
	       },
		   mouseout: function(){
			    $(this).siblings('.second-menu').toggle();
		   }
	     });
		 
	     var secondMenu = '<div class="second-menu">大家好我是</div>';
	     $node.append(secondMenuIcon).append(secondMenu);
      },
      'draggable': true,
      'dropCriteria': function($draggedNode, $dragZone, $dropZone) {
        if($draggedNode.find('.content').text().indexOf('manager') > -1 && $dropZone.find('.content').text().indexOf('engineer') > -1) {
          return false;
        }
        return true;
      }
    })
    .on('contextmenu','.node',function(ev){
      var oEvent= ev||event;
      var oUl=document.getElementById("contextDiv");
      oUl.style.display="block";
      oUl.style.left=oEvent.clientX+'px';
      oUl.style.top=oEvent.clientY+'px';
      clicknode = $(this)[0].id
      // clicknode = $(this)
      return false;
    })
    .children('.orgchart').on('nodedropped.orgchart', function(event) {
      console.log('draggedNode:' + event.draggedNode.children('.title').text()
        + ', dragZone:' + event.dragZone.children('.title').text()
        + ', dropZone:' + event.dropZone.children('.title').text()
      );
    })
    
  }

  var disnone = function(){
    var oUl=document.getElementById("contextDiv");
    oUl.style.display="none";
  }

  var erg = function(obj,id){
    var fobj
    findobj(obj,id)
    function findobj(obj,id){
      if(Object.prototype.toString.call(obj)==="[object Object]"){
        if(obj.id === id){
          fobj= obj;
        }else if(obj.children){
          findobj(obj.children,id);
        }
      }else{
        for(var i in obj){
          findobj(obj[i],id);
        }
      }
    }
    return fobj;
  }

  // 以上事封装的方法，下面是具体执行的顺序
  // 设置一些常用量
  var data = getdatasource()
  var clicknode = '';
  renderpic(data)
  // 给标签绑定增加事件
  $('#add-node').on('click', function() {
    disnone();
    var name = '新的节点' //默认name
    var content = '节点描述' // 默认content
    // console.log(clicknode +','+ name +','+ content)
    //调用接口返回新的id,cid

    //调用接口返回新的id,cid
    addnode(name, content, getId(), clicknode)//此处先用getId()代替
   })
  // 给标签绑定增加事件

  // 绑定删除事件
  $('#delete-node').on('click', function() {
    disnone()
    //因为可以修改节点内容，不允许删除根节点
    //调用接口通知后台
    
    //调用接口通知后台
    data = deletenode(clicknode);
  });
  // 绑定删除事件

  // body绑定disnone事件，用于隐藏contextnemu
  $('body').on('click',disnone)
  // body绑定disnone事件，用于隐藏contextnemu
  
  // 绑定修改属性事件
  $('#change-props').on('click',function(){
	  debugger
    $('#itemid').val(clicknode);
    data = getdata();
    // console.log(erg(data,clicknode))
    $('#itemname').val(erg(data,clicknode).name)
    $('#itemcontent').val(erg(data,clicknode).content)
    //console.log(getdata())
    //调用接口，返回数据
    $('#myModal').modal({show:true})
    
  })
  // 绑定修改属性事件


  $('#savechange').on('click',function(){
    //保存之后重新渲染
    erg(data,clicknode).name = $('#itemname').val()
    erg(data,clicknode).content = $('#itemcontent').val()
    // console.log(data)
    renderpic(data);
  })

  $('#godetail').on('cilck',function(){console.log(top.ifame5)});

  $('#getData').on('click',function(){
    disnone()
    if(clicknode){
      window.location.href = 'detail.html?id='+clicknode; 
    }
  })

