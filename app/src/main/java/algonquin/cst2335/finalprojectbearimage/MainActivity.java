package algonquin.cst2335.finalprojectbearimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import algonquin.cst2335.finalprojectbearimage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    EditText imageWidth;
    EditText imageHeight;
    Button imageButton;
    ImageView imageBear;
    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);


        imageWidth = binding.editTextWidth;
        imageHeight = binding.editTextHeight;
        imageButton = binding.buttonGenerateImage;
        imageBear = binding.imageViewBear;


        queue = Volley.newRequestQueue(this);
        imageButton.setOnClickListener(click -> {
            String width = imageWidth.getText().toString();
            String height = imageHeight.getText().toString();

            if (!height.isEmpty() && !width.isEmpty()) {
                String bearURL = "https://placebear.com/" + width + "/" + height;
                ImageRequest imgReq = new ImageRequest(bearURL, new Response.Listener<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageBear.setImageBitmap(bitmap);
                    }}, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {
                    error.printStackTrace();
                });


                queue.add(imgReq);}
            else if (height.isEmpty() && !width.isEmpty()) {
                Toast.makeText(this, "Invalid, enter image height", Toast.LENGTH_SHORT).show();}

            else if (!height.isEmpty() && width.isEmpty()) {
                Toast.makeText(this, "Invalid, enter image width", Toast.LENGTH_SHORT).show();}

            else {Toast.makeText(this, "Invalid, enter image dimensions", Toast.LENGTH_SHORT).show();}

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.help) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This App generates a bear image. Set a positive width and height," +
                            " then click generate for a bear image. You can look at all images you generated" +
                            " by clicking the Show All Generated button! ")
                    .setTitle("Help Menu")
                    .setPositiveButton("Continue", (dialog, cl) -> {
                    }).show();
        }

        return true;
    }






}