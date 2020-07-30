package com.casetools.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Recommendationpage extends AppCompatActivity {
    TextView t;
String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendationpage);
        t=findViewById(R.id.recom);
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("status");
        if(text.equalsIgnoreCase("overweight")){
        t.setText("Grains:\n" +
                "Whole-grain breads, cereals, pasta, and brown rice\n" +
                "Low-fat crackers and pretzels\n" +
                "Vegetables and fruits:\n" +
                "Fresh, frozen, or canned vegetables (no salt or low-sodium)\n" +
                "Fresh, frozen, dried, or canned fruit (canned in light syrup or fruit juice)\n" +
                "Avocado\n" +
                "Low-fat dairy products:\n" +
                "Nonfat (skim) or 1% milk\n" +
                "Nonfat or low-fat cheese, yogurt, and cottage cheese\n" +
                "Meats and proteins:\n" +
                "Chicken or turkey with no skin\n" +
                "Baked or broiled fish\n" +
                "Lean beef and pork (loin, round, extra lean hamburger)\n" +
                "Beans and peas, unsalted nuts, soy products\n" +
                "Egg whites and substitutes\n" +
                "Seeds and nuts\n" +
                "Fats:\n" +
                "Unsaturated oil, such as canola, olive, peanut, soybean, or sunflower oil\n" +
                "Soft or liquid margarine and vegetable oil spread\n" +
                "Low-fat salad dressing");}
        if(text.equalsIgnoreCase("underweight")){
        t.setText("The most important thing you can do to gain weight is to create a calorie surplus, meaning you eat more calories than your body needs.\n"+"Here are some energy-dense foods that are perfect for gaining weight:\n" +
                "\n" +
                "Nuts: Almonds, walnuts, macadamia nuts, peanuts, etc.\n" +
                "Dried fruit: Raisins, dates, prunes and others.\n" +
                "High-fat dairy: Whole milk, full-fat yogurt, cheese, cream.\n" +
                "Fats and oils: Extra virgin olive oil and avocado oil.\n" +
                "Grains: Whole grains like oats and brown rice.\n" +
                "Meat: Chicken, beef, pork, lamb, etc. Choose fattier cuts.\n" +
                "Tubers: Potatoes, sweet potatoes and yams.\n" +
                "Dark chocolate, avocados, peanut butter, coconut milk, granola, trail mixes.");}
        if(text.equalsIgnoreCase("normal")){
        t.setText("You are healthy and have a good BMI. Just go on consuming foods with low oil and fat content and just a 30-min walk daily is all you need to do!");}



    }
}
