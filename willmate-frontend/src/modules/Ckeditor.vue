<template>
  <div id="editor">
    <ckeditor :disabled="props.disabled" :config="editorConfig" v-model="editorData"></ckeditor>
  </div>
</template>

<script lang="ts" setup>

  import {ref} from "vue";

  // import ClassicEditor from '@ckeditor/ckeditor5-editor-classic/src/classiceditor';
  import {Essentials} from '@ckeditor/ckeditor5-essentials';
  import {Bold, Italic, Strikethrough, Underline} from '@ckeditor/ckeditor5-basic-styles';
  import {Paragraph} from '@ckeditor/ckeditor5-paragraph';
  import {SimpleUploadAdapter} from '@ckeditor/ckeditor5-upload';
  import {
    Table,
    TableCaption,
    TableCellProperties,
    TableColumnResize,
    TableProperties,
    TableSelection,
    TableToolbar,
    TableUI
  } from '@ckeditor/ckeditor5-table';
  import ImageUploadProgress from '@ckeditor/ckeditor5-image/src/imageupload/imageuploadprogress';
  import ImageUploadEditing from '@ckeditor/ckeditor5-image/src/imageupload/imageuploadediting';
  import ImageUpload from '@ckeditor/ckeditor5-image/src/imageupload';
  import ImageUploadUI from '@ckeditor/ckeditor5-image/src/imageupload/imageuploadui';
  import {
    Image,
    ImageCaption,
    ImageInsert,
    ImageResize,
    ImageResizeEditing,
    ImageResizeHandles,
    ImageStyle,
    ImageToolbar
  } from '@ckeditor/ckeditor5-image';
  import {AutoLink, Link, LinkImage} from '@ckeditor/ckeditor5-link';
  import {Heading} from '@ckeditor/ckeditor5-heading';
  import {Alignment} from '@ckeditor/ckeditor5-alignment';
  import {Font} from '@ckeditor/ckeditor5-font';
  import {BlockQuote} from '@ckeditor/ckeditor5-block-quote';
  import {Indent, IndentBlock} from '@ckeditor/ckeditor5-indent';
  import {List, ListProperties} from '@ckeditor/ckeditor5-list';
  import {
    SpecialCharacters,
    SpecialCharactersCurrency,
    SpecialCharactersMathematical
  } from '@ckeditor/ckeditor5-special-characters';
  import {FindAndReplace} from '@ckeditor/ckeditor5-find-and-replace';
  import {PageBreak} from '@ckeditor/ckeditor5-page-break';
  import {SourceEditing} from '@ckeditor/ckeditor5-source-editing';
  import {GeneralHtmlSupport} from '@ckeditor/ckeditor5-html-support';
  import {HtmlEmbed} from '@ckeditor/ckeditor5-html-embed';
  import {defineExpose, defineProps} from "vue";

  const props = defineProps({
    disabled: {
      type: Boolean,
      require: true
    }
  })

  let editorData = ref('');
  let editorConfig = {
    mediaEmbed: {
      previewsInData: true,
    },
    isEnabled: true,
    fontSize: {
      options: [
        9,
        11,
        13,
        15,
        17,
        19,
        21
      ]
    },
    list: {
      properties: {
        styles: true,
        startIndex: true,
        reversed: true
      }
    },
    image: {
      resize: true,
      toolbar: ['toggleImageCaption', 'imageTextAlternative', '|', 'imageStyle:alignLeft', 'imageStyle:alignCenter', 'imageStyle:alignRight'],
    },
    table: {
      contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableCellProperties', 'tableProperties']
    },
    plugins: [
      ImageUploadProgress,
      ImageUploadEditing,
      ImageUploadUI,
      ImageUpload,
      Image, ImageToolbar, ImageCaption, ImageStyle, ImageResize, LinkImage, ImageResizeEditing, ImageResizeHandles,
      Essentials,
      Bold,
      Italic,
      Underline,
      Strikethrough,
      Link, AutoLink,
      Paragraph,
      SimpleUploadAdapter,
      Table, TableColumnResize, TableCaption, TableSelection,
      TableCellProperties, TableProperties, TableToolbar, TableUI,
      Heading,
      Alignment,
      Font,
      BlockQuote,
      Indent, IndentBlock,
      List,
      ListProperties,
      SpecialCharacters, SpecialCharactersCurrency, SpecialCharactersMathematical,
      FindAndReplace,
      PageBreak,
      ImageInsert,
      SourceEditing,
      GeneralHtmlSupport,
      HtmlEmbed,
    ],
    toolbar: {
      items: [
        'heading',
        '|',
        'sourceEditing',
        '|',
        'htmlEmbed',
        '|',
        'fontSize',
        'fontFamily',
        '|',
        'fontColor',
        'fontBackgroundColor',
        '|',
        'bold',
        'italic',
        'underline',
        'strikethrough',
        '|',
        'alignment',
        '|',
        'bulletedList', 'numberedList',
        '|',
        'uploadImage', 'insertImage',
        'outdent', 'indent',
        '|',
        'findAndReplace',
        'specialCharacters',
        'link',
        'blockQuote',
        'undo',
        'redo',
        'pageBreak',
        'insertTable',

      ]
    },
    htmlEmbed: {
      showPreviews: true,
    },
    simpleUpload: {
      uploadUrl: 'https://api-dev.willmate.jp/admin/upload/image',
      withCredentials: false,
      headers: {
        'Authorization': `Bearer jcsdjrfgdrgj`
      }
    },
    link: {
      addTargetToExternalLinks: true
    },
    language: 'ja',
    addMainLanguageTranslationsToAllAssets: true,
    htmlSupport: {
      allow: [
        {
          name: /.*/,
          attributes: true,
          classes: true,
          styles: true
        },
      ],
    }

  }

  function getContent() {
    return editorData.value;
  }

  function setContent(value: string) {
    console.log("value: ", value)
    editorData.value = value;
  }

  defineExpose({getContent, setContent})

</script>

<style>
  #editor {
    width: 100%;
  }

  .ck-editor__editable_inline {
    max-height: 550px;
    height: 550px;
  }

  .ck.ck-balloon-panel {
    z-index: 99999999 !important;
  }

  .ck.ck-content ul,
  .ck.ck-content ul li {
    list-style-type: inherit;
  }

  .ck .ck-content ul, .ck .ck-content ol {
    display: block;
    padding-inline-start: 40px;
  }

  #editor a {
    color: #1b3af2;
  }
</style>
