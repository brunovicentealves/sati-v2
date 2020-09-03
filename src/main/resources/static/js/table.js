$(document).ready(function() {
			$('#todotable').DataTable({
				"ajax" : {
					"url" : "data/gettodos",
					"dataSrc" : ""
				},
				"columns" : [ {
					"data" : "description"
				}, {
					"data" : "status"
				}, {
					"data" : "createdDate"
				}, {
					"data" : "createdBy"
				} ]
			});
		});