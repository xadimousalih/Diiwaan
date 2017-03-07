package sn.tab28.diwanou.quranya1;

/**
 * @author xadimouSALIH
 * http://www.tab28.com
 */

import sn.tab28.diwanou.quranya1.utils.AboutDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public abstract class DashBoardActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void setHeader(String title, boolean btnHomeVisible,
			boolean btnFeedbackVisible) {
		ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
		View inflated = stub.inflate();

		TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
		txtTitle.setText(title);

		Button btnHome = (Button) inflated.findViewById(R.id.btnHome);
		if (!btnHomeVisible)
			btnHome.setVisibility(View.INVISIBLE);

		Button btnFeedback = (Button) inflated.findViewById(R.id.btnFeedback);
		if (!btnFeedbackVisible)
			btnFeedback.setVisibility(View.INVISIBLE);

	}

	/**
	 * Home button click handler
	 * 
	 * @param v
	 */
	public void btnHomeClick(View v) {
		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	/**
	 * Feedback button click handler
	 * 
	 * @param v
	 */
	public void btnFeedbackClick(View v) {
		AboutDialog about = new AboutDialog(this);
		about.setTitle(Html.fromHtml(this.getString(R.string.app_about)));
		about.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			openOptionsDialog();
			return true;
		case R.id.app_exit:
			exitOptionsDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void exitOptionsDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.str_exit)
				.setMessage(R.string.app_exit_message)
				.setNegativeButton(R.string.str_no,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})
				.setPositiveButton(R.string.str_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								onDestroy();
								finish();
								System.exit(0);
							}
						}).show();
	}

	private void openOptionsDialog() {
		AboutDialog about = new AboutDialog(this);
		about.setTitle(Html.fromHtml(this.getString(R.string.app_about)));
		about.show();
	}

}