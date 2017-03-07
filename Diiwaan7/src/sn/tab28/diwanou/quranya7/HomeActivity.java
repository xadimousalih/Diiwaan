package sn.tab28.diwanou.quranya7;

/**
 * @author xadimouSALIH
 * @Link http://www.tab28.com
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import sn.tab28.diwanou.quranya7.utils.ActionItem;
import sn.tab28.diwanou.quranya7.utils.QuickAction;
import sn.tab28.diwanou.quranya7.utils.UnzipUtil;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends DashBoardActivity {
	private boolean externalStorageAvailable, externalStorageWriteable;
	private DownloadManager mgr = null;
	private long lastDownload = -1L;
	private ProgressDialog mProgressDialog;
	String unzipLocation = Environment.getExternalStorageDirectory()
			+ File.separator + "diwanouquranya" + File.separator;
	String zipFile = Environment.getExternalStorageDirectory() + File.separator
			+ "diwanouquranya" + File.separator + "diwan7.zip";
	static File root = Environment.getExternalStorageDirectory();
	static String mypath = root.getAbsolutePath() + File.separator
			+ "diwanouquranya" + File.separator + "diwan7" + File.separator;
	private static final String path = new String(mypath);
	File pdfFolder = null;
	String pdfURL = null;
	private static final int ID_PART1 = 1;
	private static final int ID_PART2 = 2;

	private static final int ID_PART3 = 3;
	private static final int ID_PART4 = 4;

	private static final int ID_PART5 = 5;
	private static final int ID_PART6 = 6;

	private static final int ID_PART7 = 7;
	private static final int ID_PART8 = 8;

	private static final int ID_PART9 = 9;
	private static final int ID_PART10 = 10;

	private static final int ID_PART11 = 11;
	private static final int ID_PART12 = 12;

	private static final int ID_PART13 = 13;
	private static final int ID_PART14 = 14;

	QuickAction jukky1, jukky2, jukky3, jukky4, jukky5, jukky6, jukky7;

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
				HomeActivity.this);
		alertDialog2.setTitle(R.string.title);
		alertDialog2.setMessage(R.string.app_exit_message);
		alertDialog2.setIcon(R.drawable.ic_launcher);
		alertDialog2.setPositiveButton(R.string.str_yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						onDestroy();
					}
				});
		alertDialog2.setNegativeButton(R.string.str_no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDialog2.show();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		if (ifExistFile(path)) {
			mgr = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
			registerReceiver(onComplete, new IntentFilter(
					DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			registerReceiver(onNotificationClick, new IntentFilter(
					DownloadManager.ACTION_NOTIFICATION_CLICKED));

			ActionItem part1Item = new ActionItem(ID_PART1, getResources()
					.getString(R.string.part1), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part2Item = new ActionItem(ID_PART2, getResources()
					.getString(R.string.part2), getResources().getDrawable(
					R.drawable.menub));

			ActionItem part3Item = new ActionItem(ID_PART3, getResources()
					.getString(R.string.part3), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part4Item = new ActionItem(ID_PART4, getResources()
					.getString(R.string.part4), getResources().getDrawable(
					R.drawable.menub));
			ActionItem part5Item = new ActionItem(ID_PART5, getResources()
					.getString(R.string.part5), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part6Item = new ActionItem(ID_PART6, getResources()
					.getString(R.string.part6), getResources().getDrawable(
					R.drawable.menub));
			ActionItem part7Item = new ActionItem(ID_PART7, getResources()
					.getString(R.string.part7), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part8Item = new ActionItem(ID_PART8, getResources()
					.getString(R.string.part8), getResources().getDrawable(
					R.drawable.menub));
			ActionItem part9Item = new ActionItem(ID_PART9, getResources()
					.getString(R.string.part9), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part10Item = new ActionItem(ID_PART10, getResources()
					.getString(R.string.part10), getResources().getDrawable(
					R.drawable.menub));
			ActionItem part11Item = new ActionItem(ID_PART11, getResources()
					.getString(R.string.part11), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part12Item = new ActionItem(ID_PART12, getResources()
					.getString(R.string.part12), getResources().getDrawable(
					R.drawable.menub));
			ActionItem part13Item = new ActionItem(ID_PART13, getResources()
					.getString(R.string.part13), getResources().getDrawable(
					R.drawable.menua));
			ActionItem part14Item = new ActionItem(ID_PART14, getResources()
					.getString(R.string.part14), getResources().getDrawable(
					R.drawable.menub));

			part1Item.setSticky(true);
			part2Item.setSticky(true);
			part3Item.setSticky(true);
			part4Item.setSticky(true);
			part5Item.setSticky(true);
			part6Item.setSticky(true);
			part7Item.setSticky(true);
			part8Item.setSticky(true);
			part9Item.setSticky(true);
			part10Item.setSticky(true);
			part11Item.setSticky(true);
			part12Item.setSticky(true);
			part13Item.setSticky(true);
			part14Item.setSticky(true);

			jukky1 = new QuickAction(this, QuickAction.VERTICAL);
			jukky1.addActionItem(part1Item);
			jukky1.addActionItem(part2Item);
			jukky1.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {

				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky1.getActionItem(pos);
					if (actionId == ID_PART1) {
						if (canDisplayPdf(HomeActivity.this)) {
							CopyAssets("diwan.pdf");
						}
					} else if (actionId == ID_PART2) {
						jukky1.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_1.pdf";
						pdfAction(getFileName(pdfURL));

					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky2 = new QuickAction(this, QuickAction.VERTICAL);
			jukky2.addActionItem(part3Item);
			jukky2.addActionItem(part4Item);
			jukky2.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky2.getActionItem(pos);
					if (actionId == ID_PART3) {
						jukky2.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_2.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART4) {
						jukky2.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_3.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky3 = new QuickAction(this, QuickAction.VERTICAL);
			jukky3.addActionItem(part5Item);
			jukky3.addActionItem(part6Item);
			jukky3.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky3.getActionItem(pos);
					if (actionId == ID_PART5) {
						jukky3.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_4.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART6) {
						jukky3.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_5.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky4 = new QuickAction(this, QuickAction.VERTICAL);
			jukky4.addActionItem(part7Item);
			jukky4.addActionItem(part8Item);
			jukky4.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky4.getActionItem(pos);
					if (actionId == ID_PART7) {
						jukky4.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_6.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART8) {
						jukky4.dismiss();
						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_7.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky5 = new QuickAction(this, QuickAction.VERTICAL);
			jukky5.addActionItem(part9Item);
			jukky5.addActionItem(part10Item);
			jukky5.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky5.getActionItem(pos);
					if (actionId == ID_PART9) {
						jukky5.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_8.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART10) {
						jukky5.dismiss();
						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_9.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky6 = new QuickAction(this, QuickAction.VERTICAL);
			jukky6.addActionItem(part11Item);
			jukky6.addActionItem(part12Item);
			jukky6.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky6.getActionItem(pos);
					if (actionId == ID_PART11) {
						jukky6.dismiss();

						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_10.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART12) {
						jukky6.dismiss();
						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_11.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			jukky7 = new QuickAction(this, QuickAction.VERTICAL);
			jukky7.addActionItem(part13Item);
			jukky7.addActionItem(part14Item);
			jukky7.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					ActionItem actionItem = jukky6.getActionItem(pos);
					if (actionId == ID_PART13) {
						jukky7.dismiss();
						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_12.pdf";
						pdfAction(getFileName(pdfURL));
					} else if (actionId == ID_PART14) {
						jukky7.dismiss();
						pdfURL = "http://www.daaraykamil.com/app_files/diwan7/diwan7_13.pdf";
						pdfAction(getFileName(pdfURL));
					} else {
						Toast.makeText(
								getApplicationContext(),
								actionItem.getTitle()
										+ " "
										+ HomeActivity.this
												.getString(R.string.selected),
								Toast.LENGTH_SHORT).show();
					}
				}
			});

			setHeader(getString(R.string.infos), false, true);
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "diwanouquranya" + File.separator
					+ "diwan7" + File.separator + "unzipok.out");

			if (!file.exists()) {
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						HomeActivity.this);
				alertDialog2.setTitle(R.string.title);
				alertDialog2.setMessage(R.string.tele);
				alertDialog2.setIcon(R.drawable.ic_launcher);
				alertDialog2.setPositiveButton(R.string.str_yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (isOnline()) {

									DownloadMapAsync mew = new DownloadMapAsync();
									mew.execute("http://www.daaraykamil.com/app_files/diwan7.zip");

								} else {
									Toast.makeText(
											HomeActivity.this,
											HomeActivity.this
													.getString(R.string.no_connection),
											Toast.LENGTH_LONG).show();
								}
							}
						});
				alertDialog2.setNegativeButton(R.string.str_no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				alertDialog2.show();
			}
		} else {
			final AlertDialog builder = new AlertDialog.Builder(this).create();
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle(R.string.title);
			builder.setMessage(HomeActivity.this.getString(R.string.aver));
			builder.setCancelable(true);
			builder.setButton(HomeActivity.this.getString(R.string.str_ok),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(HomeActivity.this, R.string.ess,
									Toast.LENGTH_LONG).show();
							onDestroy();
						}
					});
			builder.show();
		}

	}

	public void onButtonClicker(View v) {
		switch (v.getId()) {
		case R.id.jukky1:
			jukky1.show(v);
			break;
		case R.id.jukky2:
			jukky2.show(v);
			break;
		case R.id.jukky3:
			jukky3.show(v);
			break;
		case R.id.jukky4:
			jukky4.show(v);
			break;
		case R.id.jukky5:
			jukky5.show(v);
			break;
		case R.id.jukky6:
			jukky6.show(v);
			break;
		case R.id.jukky7:
			jukky7.show(v);
			break;
		default:
			break;
		}
	}

	public boolean existFile(String folderURL) {
		boolean isExist = false;
		File dir = null;
		try {
			dir = new File(folderURL);
			if (dir.exists())
				isExist = true;
			else {
				if (isExternalStorageAvailableAndWriteable()) {
					dir.mkdirs();
					isExist = true;
				} else {
					final AlertDialog builder = new AlertDialog.Builder(this)
							.create();
					builder.setIcon(R.drawable.ic_launcher);
					builder.setTitle(R.string.title);
					builder.setMessage(HomeActivity.this
							.getString(R.string.imp));
					builder.setCancelable(true);
					builder.show();
					final Timer t = new Timer();
					t.schedule(new TimerTask() {
						public void run() {
							builder.dismiss();
							t.cancel();
						}
					}, 15000);
					Intent intent = getIntent();
					finish();
					startActivity(intent);
				}
			}
		} catch (Exception e) {
			Log.d("existFile", e.getMessage());
		}
		return isExist;
	}

	public boolean ifExistFile(String folderURL) {
		boolean isExist = false;
		File dir = null;
		try {
			dir = new File(folderURL);
			if (dir.exists())
				isExist = true;
			else {
				if (isExternalStorageAvailableAndWriteable()) {
					dir.mkdirs();
					isExist = true;
				}
			}
		} catch (Exception e) {
			Log.d("existFile", e.getMessage());
		}
		return isExist;
	}

	private void checkStorage() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			externalStorageAvailable = externalStorageWriteable = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			externalStorageAvailable = true;
			externalStorageWriteable = false;
		} else {
			externalStorageAvailable = externalStorageWriteable = false;
		}
	}

	public boolean isExternalStorageAvailableAndWriteable() {
		checkStorage();
		if (!externalStorageAvailable) {
			return false;
		} else if (!externalStorageWriteable) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isOnline() {
		try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnected()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void pdfAction(String nomPdf) {
		if (searchPDFFile(path, nomPdf)) {
			File externalFile = new File(path, nomPdf);
			Uri external = Uri.fromFile(externalFile);
			viewPdf(external);
		} else {
			if (isOnline()) {
				// if (isOnline()) {
				try {
					queryStatus(getCurrentFocus());
				} catch (CursorIndexOutOfBoundsException e) {
				} catch (Exception e) {
				}
				// } else {
				// Toast.makeText(HomeActivity.this,
				// HomeActivity.this.getString(R.string.no_connection),
				// Toast.LENGTH_LONG).show();
				// }

				final AlertDialog builder = new AlertDialog.Builder(this)
						.create();
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(R.string.title);
				builder.setMessage(HomeActivity.this.getString(R.string.inf));
				builder.setCancelable(true);
				builder.show();
				final Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
						builder.dismiss();
						t.cancel();
					}
				}, 15000);
				startDownload(pdfURL);
			} else {
				Toast.makeText(HomeActivity.this,
						HomeActivity.this.getString(R.string.no_connection),
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public String getFileName(String url) {
		int taille = url.length();
		int positionDernierSlash = url.lastIndexOf("/");
		String fileName = url.substring(positionDernierSlash + 1, taille);
		return fileName;
	}

	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".pdf") || name.endsWith(".PDF"));
		}
	}

	public boolean searchPDFFile(String path, String pdfName) {
		if (existFile(path)) {
			pdfFolder = new File(path);
			if (pdfFolder.listFiles(new FileExtensionFilter()).length > 0) {
				for (File file : pdfFolder.listFiles(new FileExtensionFilter())) {
					String nomFichierPdf = file.getName();
					if (pdfName.equals(nomFichierPdf)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void viewPdf(Uri file) {
		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(file, "application/pdf");
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.nopdf);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setMessage(HomeActivity.this.getString(R.string.teleplay));
			builder.setPositiveButton(
					HomeActivity.this.getString(R.string.str_yes),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent marketIntent = new Intent(Intent.ACTION_VIEW);
							marketIntent.setData(Uri
									.parse("market://details?id=com.adobe.reader"));
							startActivity(marketIntent);
						}
					});
			builder.setNegativeButton(
					HomeActivity.this.getString(R.string.str_no), null);
			builder.create().show();
		}
	}

	@Override
	public void onDestroy() {
		try {
			unregisterReceiver(onComplete);
			unregisterReceiver(onNotificationClick);
		} catch (IllegalArgumentException e) {
		}
		finish();
		moveTaskToBack(true);
		super.onDestroy();

	}

	public void startDownload(String url) {
		Uri uri = Uri.parse(url);
		Uri destinationUri = Uri.parse("file://" + path + getFileName(url));
		lastDownload = mgr.enqueue(new DownloadManager.Request(uri)
				.setAllowedNetworkTypes(
						DownloadManager.Request.NETWORK_WIFI
								| DownloadManager.Request.NETWORK_MOBILE)
				.setAllowedOverRoaming(false).setTitle(getFileName(url))
				.setDescription("DaarayKamil")
				.setDestinationUri(destinationUri));

	}

	public void queryStatus(View v) {
		Cursor c = mgr.query(new DownloadManager.Query()
				.setFilterById(lastDownload));

		if (c == null) {
			Toast.makeText(this, "Download not found!", Toast.LENGTH_LONG)
					.show();
		} else {
			c.moveToFirst();

			Log.d(getClass().getName(),
					"COLUMN_ID: "
							+ c.getLong(c
									.getColumnIndex(DownloadManager.COLUMN_ID)));
			Log.d(getClass().getName(),
					"COLUMN_BYTES_DOWNLOADED_SO_FAR: "
							+ c.getLong(c
									.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)));
			Log.d(getClass().getName(),
					"COLUMN_LAST_MODIFIED_TIMESTAMP: "
							+ c.getLong(c
									.getColumnIndex(DownloadManager.COLUMN_LAST_MODIFIED_TIMESTAMP)));
			Log.d(getClass().getName(),
					"COLUMN_LOCAL_URI: "
							+ c.getString(c
									.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
			Log.d(getClass().getName(),
					"COLUMN_STATUS: "
							+ c.getInt(c
									.getColumnIndex(DownloadManager.COLUMN_STATUS)));
			Log.d(getClass().getName(),
					"COLUMN_REASON: "
							+ c.getInt(c
									.getColumnIndex(DownloadManager.COLUMN_REASON)));

			Toast.makeText(this, statusMessage(c), Toast.LENGTH_SHORT).show();
		}
	}

	public void viewLog(View v) {
		startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
	}

	private String statusMessage(Cursor c) {
		String msg = "";

		switch (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
		case DownloadManager.STATUS_FAILED:
			msg = HomeActivity.this.getString(R.string.dof);
			break;

		case DownloadManager.STATUS_PAUSED:
			msg = HomeActivity.this.getString(R.string.dopa);
			;
			break;

		case DownloadManager.STATUS_PENDING:
			msg = HomeActivity.this.getString(R.string.dope);
			;
			break;

		case DownloadManager.STATUS_RUNNING:
			msg = HomeActivity.this.getString(R.string.dinp);
			;
			break;

		case DownloadManager.STATUS_SUCCESSFUL:
			msg = HomeActivity.this.getString(R.string.doc);
			;
			break;

		default:
			msg = HomeActivity.this.getString(R.string.doin);
			;
			break;
		}

		return (msg);
	}

	BroadcastReceiver onComplete = new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			Toast.makeText(ctxt, R.string.tdone, Toast.LENGTH_LONG).show();
		}
	};

	BroadcastReceiver onNotificationClick = new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			Toast.makeText(ctxt, R.string.tcc, Toast.LENGTH_LONG).show();

		}
	};
	public static final String MIME_TYPE_PDF = "application/pdf";

	public static boolean canDisplayPdf(Context context) {
		PackageManager packageManager = context.getPackageManager();
		Intent testIntent = new Intent(Intent.ACTION_VIEW);
		testIntent.setType(MIME_TYPE_PDF);
		if (packageManager.queryIntentActivities(testIntent,
				PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void CopyAssets(final String path) {

		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), path);
		try {
			in = assetManager.open(path);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + path),
				MIME_TYPE_PDF);

		startActivity(intent);
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	class DownloadMapAsync extends AsyncTask<String, String, String> {
		String result = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(HomeActivity.this);
			mProgressDialog.setMessage(HomeActivity.this
					.getString(R.string.ddz));
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... aurl) {
			int count;
			try {
				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();
				int lenghtOfFile = conexion.getContentLength();
				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(zipFile);
				byte data[] = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				output.close();
				input.close();
				result = "true";

			} catch (Exception e) {

				result = "false";
			}
			return null;

		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			mProgressDialog.dismiss();
			if (result.equalsIgnoreCase("true")) {
				try {
					unzip();
					if (!Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
					} else {

						File file = new File(
								Environment.getExternalStorageDirectory()
										+ File.separator + "diwanouquranya"
										+ File.separator + "diwan7"
										+ File.separator + "unzipok.out");
						file.mkdirs();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

			}
		}
	}

	public void unzip() throws IOException {
		mProgressDialog = new ProgressDialog(HomeActivity.this);
		mProgressDialog.setMessage(HomeActivity.this
				.getString(R.string.extractzip));
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		new UnZipTask().execute(zipFile, unzipLocation);
	}

	private class UnZipTask extends AsyncTask<String, Void, Boolean> {
		@SuppressWarnings("rawtypes")
		@Override
		protected Boolean doInBackground(String... params) {
			String filePath = params[0];
			String destinationPath = params[1];

			File archive = new File(filePath);
			try {

				ZipFile zipfile = new ZipFile(archive);
				for (Enumeration e = zipfile.entries(); e.hasMoreElements();) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					unzipEntry(zipfile, entry, destinationPath);
				}

				UnzipUtil d = new UnzipUtil(zipFile, unzipLocation);
				d.unzip();

			} catch (Exception e) {

				return false;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "diwanouquranya" + File.separator
					+ "diwan7.zip");
			if (file.exists()) {
				boolean deleted = file.delete();
				Log.d("File Deleted", "" + deleted);
			}
			mProgressDialog.dismiss();
		}

		/**
		 * 
		 * @param zipfile
		 * @param entry
		 * @param outputDir
		 * @throws IOException
		 */
		private void unzipEntry(ZipFile zipfile, ZipEntry entry,
				String outputDir) throws IOException {

			if (entry.isDirectory()) {
				createDir(new File(outputDir, entry.getName()));
				return;
			}

			File outputFile = new File(outputDir, entry.getName());
			if (!outputFile.getParentFile().exists()) {
				createDir(outputFile.getParentFile());
			}

			BufferedInputStream inputStream = new BufferedInputStream(
					zipfile.getInputStream(entry));
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(outputFile));

			try {

			} finally {
				outputStream.flush();
				outputStream.close();
				inputStream.close();

			}
		}

		/**
		 * 
		 * @param dir
		 */
		private void createDir(File dir) {
			if (dir.exists()) {
				return;
			}
			if (!dir.mkdirs()) {
				throw new RuntimeException("Can not create dir " + dir);
			}
		}
	}

}
