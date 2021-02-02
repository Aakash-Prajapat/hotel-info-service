import org.springframework.cloud.contract.spec.Contract

Contract.make {
   request {
       method 'GET'
       urlPath('/hotelInfo/getByCity/Indore')
   }
   response {
       status OK()
        body("""{
  			"data": {
    			"hotels": [
      						{
        						"hotelName": "Sayaji",
                				"city": "Indore"
               				},
               				{
        						"hotelName": "Radisson Hotels",
                				"city": "Indore"
               				}
               	]
        	}
        	}""")
       headers {
            contentType(applicationJson())
        }
   }
}