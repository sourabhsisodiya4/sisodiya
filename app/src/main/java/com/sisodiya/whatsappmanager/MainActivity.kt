package com.sisodiya.whatsappmanager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import java.net.URLEncoder

class MainActivity : Activity() {
    private val green = Color.rgb(7, 124, 72)
    private val dark = Color.rgb(5, 85, 51)
    private val ink = Color.rgb(28, 38, 33)
    private var english = false
    private var phone = ""
    private var message = "नमस्कार! SISODIYA TRADERS में आपका स्वागत है। हमारे यहाँ बिल्डिंग मटेरियल उपलब्ध है।"
    private lateinit var root: LinearLayout
    private lateinit var body: LinearLayout
    private val contacts = mutableListOf<Pair<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()
    private fun text(s: String, size: Float = 18f, color: Int = ink, bold: Boolean = false): TextView = TextView(this).apply {
        text = s; textSize = size; setTextColor(color); if (bold) setTypeface(null, Typeface.BOLD)
        setPadding(dp(4), dp(8), dp(4), dp(8))
    }
    private fun button(label: String, action: () -> Unit): Button = Button(this).apply {
        text = label; textSize = 16f; isAllCaps = false; setTextColor(Color.WHITE)
        backgroundTintList = android.content.res.ColorStateList.valueOf(green)
        setPadding(dp(10), dp(12), dp(10), dp(12)); setOnClickListener { action() }
    }
    private fun field(hint: String, multi: Boolean = false): EditText = EditText(this).apply {
        this.hint = hint; textSize = 17f; setTextColor(ink); setPadding(dp(12), dp(10), dp(12), dp(10))
        if (multi) { minLines = 4; gravity = Gravity.TOP; inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE }
        else inputType = InputType.TYPE_CLASS_TEXT
        background = android.graphics.drawable.GradientDrawable().apply { setColor(Color.WHITE); cornerRadius = dp(12).toFloat(); setStroke(dp(1), Color.LTGRAY) }
    }
    private fun base(title: String) {
        root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(Color.rgb(247,250,248)) }
        val header = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(18), dp(16), dp(18), dp(14)); setBackgroundColor(green) }
        header.addView(text("SISODIYA WHATSAPP MANAGER", 19f, Color.WHITE, true))
        header.addView(text(title, 23f, Color.WHITE, true))
        root.addView(header)
        val scroll = ScrollView(this)
        body = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(16), dp(12), dp(16), dp(20)) }
        scroll.addView(body); root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        val nav = LinearLayout(this).apply { setPadding(dp(5), dp(6), dp(5), dp(6)); setBackgroundColor(Color.WHITE) }
        listOf("होम", "कैंपेन", "ग्राहक", "सेटिंग").forEachIndexed { i, s ->
            val b = TextView(this).apply { text = s; textSize = 14f; gravity = Gravity.CENTER; setTextColor(green); setPadding(dp(4), dp(12), dp(4), dp(12)); setOnClickListener { when(i){0->showHome();1->showCampaign();2->showContacts();else->showSettings()} } }
            nav.addView(b, LinearLayout.LayoutParams(0, -2, 1f))
        }
        root.addView(nav); setContentView(root)
    }
    private fun gap() { body.addView(Space(this), LinearLayout.LayoutParams(1, dp(10))) }
    private fun showHome() {
        base(if (english) "Business Dashboard" else "बिजनेस डैशबोर्ड")
        body.addView(text(if (english) "Welcome to your marketing desk" else "नमस्ते! आपके बिजनेस मार्केटिंग सेंटर में स्वागत है 👋", 20f, ink, true))
        val cards = listOf(if(english) "Import Excel / CSV" else "Excel / CSV ग्राहक जोड़ें", if(english) "Create Campaign" else "नया कैंपेन बनाएं", if(english) "Contact Manager" else "कॉन्टैक्ट मैनेजर", if(english) "Message Templates" else "मैसेज टेम्पलेट", if(english) "Campaign History" else "कैंपेन हिस्ट्री", if(english) "Settings" else "सेटिंग्स")
        cards.forEachIndexed { i, label -> body.addView(button(label) { when(i){0->pickCsv();1->showCampaign();2->showContacts();3->showCampaign();4->showContacts();else->showSettings()} }); gap() }
        body.addView(text(if(english) "Note: WhatsApp opens for you to review and send each message." else "ध्यान दें: संदेश की जाँच और भेजने के लिए WhatsApp खुलेगा।", 14f, Color.DKGRAY))
    }
    private fun showCampaign() {
        base(if(english) "Create Campaign" else "नया कैंपेन")
        body.addView(text(if(english) "Customer mobile number (with country code)" else "ग्राहक का मोबाइल नंबर (देश कोड सहित)", 16f, ink, true))
        val p = field("919876543210"); body.addView(p); gap()
        body.addView(text(if(english) "Message" else "मैसेज लिखें", 16f, ink, true))
        val m = field("अपना संदेश लिखें…", true); m.setText(message); body.addView(m); gap()
        body.addView(button(if(english) "Open WhatsApp Business" else "WhatsApp Business में खोलें") {
            phone = p.text.toString().filter { it.isDigit() }; message = m.text.toString()
            if(phone.length < 10) { Toast.makeText(this, "सही मोबाइल नंबर डालें", Toast.LENGTH_LONG).show() }
            else openWhatsApp(phone, message)
        })
        gap(); body.addView(text(if(english) "You will review and tap Send inside WhatsApp. No background bulk sending is performed." else "WhatsApp में संदेश देखकर आपको खुद Send दबाना होगा। बैकग्राउंड में बल्क सेंडिंग नहीं होगी।", 14f, Color.DKGRAY))
    }
    private fun openWhatsApp(number: String, msg: String) {
        val url = "https://wa.me/$number?text=" + URLEncoder.encode(msg, "UTF-8")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { setPackage("com.whatsapp.w4b") }
        try { startActivity(intent) } catch (_: Exception) {
            try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
            catch (_: Exception) { Toast.makeText(this, "WhatsApp Business इंस्टॉल करें", Toast.LENGTH_LONG).show() }
        }
    }
    private fun showContacts() {
        base(if(english) "Contact Manager" else "कॉन्टैक्ट मैनेजर")
        body.addView(button(if(english) "Import CSV file" else "CSV Excel फाइल इम्पोर्ट करें") { pickCsv() }); gap()
        body.addView(text("कुल ग्राहक: ${contacts.size}", 19f, ink, true))
        contacts.forEach { (n, p) -> body.addView(text("$n  •  $p", 16f)) }
        if(contacts.isEmpty()) body.addView(text("अभी ग्राहक सूची खाली है। CSV फाइल इम्पोर्ट करें।", 15f, Color.DKGRAY))
    }
    private fun pickCsv() {
        val i = Intent(Intent.ACTION_OPEN_DOCUMENT).apply { addCategory(Intent.CATEGORY_OPENABLE); type = "text/*" }
        startActivityForResult(i, 101)
    }
    @Deprecated("Deprecated in Android API; retained for broad device compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                try {
                    contentResolver.openInputStream(uri)?.bufferedReader()?.useLines { lines ->
                        lines.drop(1).forEach { line ->
                            val cols = line.split(",")
                            if(cols.size >= 2) contacts.add(cols[0].trim().trim('"') to cols[1].trim().trim('"'))
                        }
                    }
                    Toast.makeText(this, "${contacts.size} ग्राहक इम्पोर्ट हुए", Toast.LENGTH_LONG).show(); showContacts()
                } catch (_: Exception) { Toast.makeText(this, "CSV पढ़ने में समस्या हुई", Toast.LENGTH_LONG).show() }
            }
        }
    }
    private fun showSettings() {
        base(if(english) "Settings" else "सेटिंग्स")
        body.addView(text(if(english) "App language" else "ऐप की भाषा", 17f, ink, true))
        body.addView(button(if(english) "Switch to Hindi" else "Switch to English") { english = !english; showSettings() })
        gap(); body.addView(text("WhatsApp Business • CSV Import • Large Buttons", 15f, Color.DKGRAY))
        body.addView(text("संस्करण 0.1 • शुरुआती प्रोटोटाइप", 14f, Color.GRAY))
    }
}
