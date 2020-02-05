/* Prototyping
/* ========================================================================== */
(function (window, ElementPrototype, ArrayPrototype, polyfill) {
	function NodeList() { [polyfill] }
	NodeList.prototype.length = ArrayPrototype.length;
	ElementPrototype.matchesSelector = ElementPrototype.matchesSelector ||
	ElementPrototype.mozMatchesSelector ||
	ElementPrototype.msMatchesSelector ||
	ElementPrototype.oMatchesSelector ||
	ElementPrototype.webkitMatchesSelector ||
	function matchesSelector(selector) {
		return ArrayPrototype.indexOf.call(this.parentNode.querySelectorAll(selector), this) > -1;
	};
	ElementPrototype.ancestorQuerySelectorAll = ElementPrototype.ancestorQuerySelectorAll ||
	ElementPrototype.mozAncestorQuerySelectorAll ||
	ElementPrototype.msAncestorQuerySelectorAll ||
	ElementPrototype.oAncestorQuerySelectorAll ||
	ElementPrototype.webkitAncestorQuerySelectorAll ||
	function ancestorQuerySelectorAll(selector) {
		for (var cite = this, newNodeList = new NodeList; cite = cite.parentElement;) {
			if (cite.matchesSelector(selector)) ArrayPrototype.push.call(newNodeList, cite);
		}
		return newNodeList;
	};
	ElementPrototype.ancestorQuerySelector = ElementPrototype.ancestorQuerySelector ||
	ElementPrototype.mozAncestorQuerySelector ||
	ElementPrototype.msAncestorQuerySelector ||
	ElementPrototype.oAncestorQuerySelector ||
	ElementPrototype.webkitAncestorQuerySelector ||
	function ancestorQuerySelector(selector) {
		return this.ancestorQuerySelectorAll(selector)[0] || null;
	};
})(this, Element.prototype, Array.prototype);
/* Helper Functions
/* ========================================================================== */
function generateTableRow() {
	var emptyColumn = document.createElement('tr');
	emptyColumn.innerHTML = '<td><a class="cut">-</a><span contenteditable></span></td>' +
		'<td><span data-prefix contenteditable></span> €</td>' +
		'<td><span contenteditable></span></td>' +
		'<td><span data-prefix></span> €</td>';
	return emptyColumn;
}
function parseFloatHTML(element) {
	return parseFloat(element.innerHTML.replace(/[^\d\.\-]+/g, '')) || 0;
}
function parsePrice(number) {
	return number.toFixed(2).replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1,');
}
/* Update Number
/* ========================================================================== */
function updateNumber(e) {
	var
	activeElement = document.activeElement,
	value = parseFloat(activeElement.innerHTML),
	wasPrice = activeElement.innerHTML == parsePrice(parseFloatHTML(activeElement));
	if (!isNaN(value) && (e.keyCode == 38 || e.keyCode == 40 || e.wheelDeltaY)) {
		e.preventDefault();
		value += e.keyCode == 38 ? 1 : e.keyCode == 40 ? -1 : Math.round(e.wheelDelta * 0.025);
		value = Math.max(value, 0);
		activeElement.innerHTML = wasPrice ? parsePrice(value) : value;
	}
	updateInvoice();
}
/* Update Invoice
/* ========================================================================== */
function updateInvoice() {
	var total = 0;
	var cells, price, total, a, i;
	// update inventory cells
	// ======================
	for (var a = document.querySelectorAll('table.inventory tbody tr'), i = 0; a[i]; ++i) {
		// get inventory row cells
		cells = a[i].querySelectorAll('span:last-child');
		// set price as cell[2] * cell[3]
		price = parseFloatHTML(cells[1]) * parseFloatHTML(cells[2]);
		// add price to total
		total += price;
		// set row total
		cells[3].innerHTML = price;
	}
	// get total cell
	cells = document.querySelectorAll('table.balance td:last-child span:last-child');
	// set total
	cells[0].innerHTML = total;
	// update price formatting
	// =======================
	for (a = document.querySelectorAll('span[data-prefix]'), i = 0; a[i]; ++i) if (document.activeElement != a[i]) a[i].innerHTML = parsePrice(parseFloatHTML(a[i]));
}
/* On Content Load
/* ========================================================================== */
function onContentLoad() {
	updateInvoice();
	function onClick(e) {
		var element = e.target.querySelector('[contenteditable]'), row;
		element && e.target != document.documentElement && e.target != document.body && element.focus();
		if (e.target.matchesSelector('.add')) {
			document.querySelector('table.inventory tbody').appendChild(generateTableRow());
		}
		else if (e.target.className == 'cut') {
			row = e.target.ancestorQuerySelector('tr');
			row.parentNode.removeChild(row);
		}
		updateInvoice();
	}
	if (window.addEventListener) {
		document.addEventListener('click', onClick);
		document.addEventListener('mousewheel', updateNumber);
		document.addEventListener('keydown', updateNumber);
		document.addEventListener('keydown', updateInvoice);
		document.addEventListener('keyup', updateInvoice);
	}
}
window.addEventListener && document.addEventListener('DOMContentLoaded', onContentLoad);

jQuery(document).ready(function($) {
	window.addEventListener && document.addEventListener('DOMContentLoaded', onContentLoad);
	var submitForm = document.getElementById("SubmitInvoice")
	submitForm.addEventListener("click", function(e){
		e.preventDefault;
		var token = $("input[name='_csrf']").attr("value");
		var htmlString = document.body.parentElement.innerHTML;

		var table = document.getElementById("inventory");
		var details = {};
		const tableDetails =[];
		for (var i = 1, row; row = table.rows[i]; i++) {
			   
			const detail = {};
			let header ="";
			   for (var j = 0, col; col = row.cells[j]; j++) {
				   
				   if (j == 0){
					   header = "description";
				   }
				   else if (j ==1){
					   header = "price";
				   }
				   else if (j==2){
					   header = "quantity";
				   }
				   else if (j==3){
					   header = "total";
				   }

				   const value = row.cells[j].querySelector("span").innerHTML;
				   detail[header] = value

			   }

			   tableDetails.push(detail);
			}
			details[`details`] = tableDetails;
			details[`editorRegId`]=editorRegId;
			
		$.ajax({
			    url : '/admin/facturePdf', 
			    headers: {"X-CSRF-TOKEN": token},
			    type : 'POST',
			    async: false,
			    data: JSON.stringify(details),
			    datatype : "application/json",
			    contentType: "application/json"
			  })			  
	})
});