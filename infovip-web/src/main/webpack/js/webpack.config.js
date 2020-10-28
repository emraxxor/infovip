const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin')

function resolve (dir) {
  return path.join(__dirname,  dir)
}


const createLintingRule = () => ({
  test: /\.(js|vue)$/,
  loader: 'eslint-loader',
  enforce: 'pre',
  include: [resolve('src'), resolve('test')],
  options: {
    formatter: require('eslint-friendly-formatter'),
    emitWarning: !config.dev.showEslintErrorsInOverlay
  }
})


module.exports = {
  mode : 'development',
  entry:     {
      app:  './src/app.js',
      PhotoCommentCore : './src/user/ui/photo/PhotoCommentCore.js',
      bootstrap : './src/bootstrap.js',
      front : './src/front.js',
      vue : './src/vue.js',
      module: './src/module.js',
      user : './src/user.js',
      tinymce : './src/tinymce.js',
      shared: 'lodash',
  },
  resolve: {
    alias: {
        'vue$': 'vue/dist/vue.esm.js',
        '@': resolve('src'),
    },
    extensions: ['*', '.js', '.vue', '.json']
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      {
        test: /\.js$/,
        loader: 'babel-loader'
      },
      {
        test: /\.css$/,
        use: [
          'vue-style-loader',
          'style-loader',
          'css-loader',
          'sass-loader'
        ]
      },
      {
        test: /\.(jpe?g|png|gif)$/,
        use: 'file-loader'
      },
      {
        test: /\.svg$/,
        use: 'file-loader'
      },
      {
        test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
        loader: 'file-loader',
      },
      {
        test: /\.s[ac]ss$/i,
        use: [
          'style-loader',
          'css-loader',
          {
            loader: 'sass-loader',
            options: {
              // Prefer `dart-sass`
              implementation: require('sass'),
            },
          },
        ],
      },
      {
        test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
        use: [
          {
            loader: 'file-loader',
          }
        ]
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin()
  ],
  output: {
    publicPath : '/resources/webpack/dist/',
    filename : '[name].bundle.js',
    path: path.resolve(__dirname + '/../../webapp/WEB-INF/resources/webpack/', 'dist'),
  },
};
