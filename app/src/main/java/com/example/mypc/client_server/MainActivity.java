package com.example.mypc.client_server;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity
{
    EditText editText;
    Button b;
    private static Socket socket;
    PrintWriter printWriter;
    final String  host="192.168.2.116";
    final int port=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.et_1);
        b=(Button)findViewById(R.id.bt_1);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myTask mt=new myTask();
                mt.execute();
                Toast.makeText(getApplicationContext(),"message send to server",Toast.LENGTH_LONG).show();

            }
        });

    }
    class myTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                socket = new Socket(host, port);
                printWriter=new PrintWriter(socket.getOutputStream());
                printWriter.write(editText.getText().toString());
                printWriter.flush();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

}
