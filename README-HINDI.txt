SISODIYA WHATSAPP MANAGER — ANDROID PROJECT

यह Android Studio का Kotlin प्रोजेक्ट है। यह अभी APK नहीं है।

शुरुआती सुविधाएँ:
- हिंदी/English बड़े बटन वाला इंटरफेस
- डैशबोर्ड और स्क्रीन नेविगेशन
- CSV ग्राहक सूची इम्पोर्ट (Excel को पहले CSV में सेव करें)
- संदेश लिखने की सुविधा
- चुने हुए ग्राहक के लिए WhatsApp Business में संदेश खोलना; भेजने से पहले आप खुद समीक्षा करके Send दबाएँ

महत्वपूर्ण सीमाएँ:
- यह शुरुआती प्रोटोटाइप है; ग्राहक डेटा अभी ऐप बंद करने पर सुरक्षित नहीं रहता।
- नियमित WhatsApp Business App के जरिए अनधिकृत bulk/background auto-send, inbox पढ़ना, delivery/read receipts और auto-reply उपलब्ध नहीं कराए गए हैं।
- CSV इम्पोर्ट शुरुआती स्तर का है; XLSX सीधे इम्पोर्ट नहीं होता।

APK बनाने के लिए:
1. इस ZIP को Android Studio वाले कंप्यूटर में Extract करें।
2. Android Studio में प्रोजेक्ट फोल्डर खोलें और Gradle Sync पूरा होने दें।
3. Build > Build Bundle(s) / APK(s) > Build APK(s) चुनें।
4. APK app/build/outputs/apk/debug/app-debug.apk में बनेगी।

केवल मोबाइल से बिल्ड करने का विकल्प:
GitHub पर इस प्रोजेक्ट की फाइलें अपलोड करके Actions में "Build Android APK" workflow चलाएँ। सफल रन के बाद artifact डाउनलोड करें।
