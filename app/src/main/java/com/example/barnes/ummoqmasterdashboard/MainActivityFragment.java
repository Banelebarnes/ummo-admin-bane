package com.example.barnes.ummoqmasterdashboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import pl.coreorb.selectiondialogs.data.SelectableColor;
import pl.coreorb.selectiondialogs.data.SelectableIcon;
import pl.coreorb.selectiondialogs.dialogs.ColorSelectDialog;
import pl.coreorb.selectiondialogs.dialogs.IconSelectDialog;
import pl.coreorb.selectiondialogs.utils.ColorPalettes;
import pl.coreorb.selectiondialogs.views.SelectedItemView;

/**
 * Created by barnes on 7/24/16.
 */
public class MainActivityFragment extends Fragment implements IconSelectDialog.OnIconSelectedListener,
        ColorSelectDialog.OnColorSelectedListener {

    private static final String TAG_SELECT_ICON_DIALOG = "TAG_SELECT_ICON_DIALOG";
    private static final String TAG_SELECT_COLOR_DIALOG = "TAG_SELECT_COLOR_DIALOG";
    private static final String TAG_SELECT_TEXT_DIALOG = "TAG_SELECT_TEXT_DIALOG";

    private SelectedItemView iconSIV;
    private SelectedItemView colorSIV;
    private SelectedItemView textSIV;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        iconSIV = (SelectedItemView) rootView.findViewById(R.id.icon_siv);
        iconSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIconSelectDialog();
            }
        });

        colorSIV = (SelectedItemView) rootView.findViewById(R.id.color_siv);
        colorSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelectDialog();
            }
        });

        textSIV = (SelectedItemView) rootView.findViewById(R.id.text_siv);
        textSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextInputDialog();
            }
        });

        //((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        IconSelectDialog iconDialog = (IconSelectDialog) getFragmentManager().findFragmentByTag(TAG_SELECT_ICON_DIALOG);
        if (iconDialog != null) {
            iconDialog.setOnIconSelectedListener(this);
        }
        ColorSelectDialog colorDialog = (ColorSelectDialog) getFragmentManager().findFragmentByTag(TAG_SELECT_COLOR_DIALOG);
        if (colorDialog != null) {
            colorDialog.setOnColorSelectedListener(this);
        }
    }

    /**
     * Displays selected icon in {@link SelectedItemView} view.
     * @param selectedItem selected {@link SelectableIcon} object containing: id, name and drawable resource id.
     */
    @Override
    public void onIconSelected(SelectableIcon selectedItem) {
        iconSIV.setSelectedIcon(selectedItem);
    }

    /**
     * Displays selected color in {@link SelectedItemView} view.
     * @param selectedItem selected {@link SelectableColor} object containing: id, name and color value.
     */
    @Override
    public void onColorSelected(SelectableColor selectedItem) {
        colorSIV.setSelectedColor(selectedItem);
    }

    /**
     * Shows icon selection dialog with sample icons.
     */
    private void showIconSelectDialog() {
        new IconSelectDialog.Builder(getContext())
                .setIcons(sampleIcons())
                .setTitle(R.string.select_grand_q_master)
                .setSortIconsByName(true)
                .setOnIconSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_ICON_DIALOG);
    }

    /**
     * Shows color selection dialog with default Material Design icons.
     */
    private void showColorSelectDialog() {
        new ColorSelectDialog.Builder(getContext())
                .setColors(ColorPalettes.loadMaterialDesignColors500(getContext(), false))
                .setTitle(R.string.select_q)
                .setSortColorsByName(true)
                .setOnColorSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_COLOR_DIALOG);
    }

    private void showTextInputDialog() {
        final EditText textET = new EditText(getContext());

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.text_input_dialog_title)
                .setView(textET)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        textSIV.setSelectedName(textET.getText().toString());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    /**
     * Creates sample ArrayList of icons to display in dialog.
     * @return sample icons
     */
    private static ArrayList<SelectableIcon> sampleIcons() {
        ArrayList<SelectableIcon> selectionDialogsColors = new ArrayList<>();
        selectionDialogsColors.add(new SelectableIcon("Luluma", "Lulama", R.drawable.ic_account_box_black_24dp));
        selectionDialogsColors.add(new SelectableIcon("Mxolosi", "Mxolisi", R.drawable.ic_account_box_black_24dp));
        selectionDialogsColors.add(new SelectableIcon("Mandla", "Manadla", R.drawable.ic_account_box_black_24dp));
        return selectionDialogsColors;
    }

    private static ArrayList<SelectableIcon> sampleQueues() {
        ArrayList<SelectableIcon> selectionDialogsColors = new ArrayList<>();
        selectionDialogsColors.add(new SelectableIcon("Deposit 1", "Deposit 1", R.drawable.ic_account_box_black_24dp));
        selectionDialogsColors.add(new SelectableIcon("Deposit 2", "Deposit 2", R.drawable.ic_account_box_black_24dp));
        selectionDialogsColors.add(new SelectableIcon("Deposit 3", "Deposit 3", R.drawable.ic_account_box_black_24dp));
        return selectionDialogsColors;
    }
}