const path = require('path');

module.exports = {
  mode : 'development',
  entry:     {
      app:  './src/app.js',
      bootstrap : './src/bootstrap.js',
      front : './src/front.js',
      vue : './src/vue.js',
      shared: 'lodash',
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
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
  output: {
    publicPath : '/resources/webpack/dist/',
    filename : '[name].bundle.js',
    path: path.resolve(__dirname + '/../../webapp/WEB-INF/resources/webpack/', 'dist'),
  },
};
