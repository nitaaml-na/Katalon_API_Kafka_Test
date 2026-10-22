import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// ==========================================
// 1. Bertindak sebagai PRODUCER (POST Request)
// ==========================================
def responsePost = WS.sendRequest(findTestObject('Object Repository/API/Create_User'))

// Verifikasi HTTP Status Code sukses untuk pembuatan data (201 Created)
WS.verifyResponseStatusCode(responsePost, 201)

// Parsing response body untuk mengambil ID dinamis yang digenerate sistem
def parser = new JsonSlurper()
def jsonPost = parser.parseText(responsePost.getResponseBodyContent())
println("=== PRODUCER LOG ===")
println("Data berhasil dibuat dengan ID: " + jsonPost.id)


// ==========================================
// 2. Bertindak sebagai CONSUMER (GET Request)
// ==========================================
// *Catatan: Pastikan URL pada Object Repository 'Get_User_List' adalah: https://reqres.in
def responseGet = WS.sendRequest(findTestObject('Object Repository/API/Get_User_List'))

// Verifikasi HTTP Status Code sukses untuk pembacaan data (200 OK)
WS.verifyResponseStatusCode(responseGet, 200)

println("=== CONSUMER LOG ===")
println("Response Body yang diterima: " + responseGet.getResponseBodyContent())

// ------------------------------------------
// VERIFIKASI METADATA & DATA VALIDASI (Sesuai Response Anda)
// ------------------------------------------

// 1. Validasi Properti Utama di level Root
WS.verifyElementPropertyValue(responseGet, 'page', 1)
WS.verifyElementPropertyValue(responseGet, 'per_page', 6)
WS.verifyElementPropertyValue(responseGet, 'total', 12)
WS.verifyElementPropertyValue(responseGet, 'total_pages', 2)

// 2. Validasi Objek Pertama di dalam Array 'data' (Index 0 - George Bluth)
WS.verifyElementPropertyValue(responseGet, 'data[0].id', 1)
WS.verifyElementPropertyValue(responseGet, 'data[0].email', 'george.bluth@reqres.in')
WS.verifyElementPropertyValue(responseGet, 'data[0].first_name', 'George')
WS.verifyElementPropertyValue(responseGet, 'data[0].last_name', 'Bluth')
WS.verifyElementPropertyValue(responseGet, 'data[0].avatar', 'https://reqres.in/img/faces/1-image.jpg')

// 3. Validasi Objek Kedua di dalam Array 'data' (Index 1 - Janet Weaver)
WS.verifyElementPropertyValue(responseGet, 'data[1].first_name', 'Janet')

// 4. Validasi Objek Meta Informasi Pendukung
WS.verifyElementPropertyValue(responseGet, '_meta.powered_by', 'ReqRes')
WS.verifyElementPropertyValue(responseGet, '_meta.context', 'keyless_success')
